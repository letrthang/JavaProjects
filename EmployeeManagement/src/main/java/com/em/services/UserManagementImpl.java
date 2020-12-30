/**
 * 
 */
package com.em.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.em.dao.UserDAO;
import com.em.dto.CreateUserReqDTO;
import com.em.dto.CreateUserResDTO;
import com.em.dto.FetchUserResDTO;
import com.em.dto.GetUserResDTO;
import com.em.dto.GlobalUserManagementException;
import com.em.dto.UpdateUserReqDTO;
import com.em.dto.UpdateUserResDTO;
import com.em.dto.UploadUsersResDTO;
import com.em.model.User;
import com.em.util.CSVUtil;
import com.em.util.DateUtils;

/**
 * @author Thang Le
 *
 */

@RestController
public class UserManagementImpl implements UserManagement {
    @Autowired
    UserDAO userDAO;

    /**
     *
     */
    @Override
    public ResponseEntity<UploadUsersResDTO> uploadUsers(MultipartFile fileUpload) {
	CSVParser csvParser = null;
	BufferedReader fileReader = null;

	try {
	    // read and parsing CSV file
	    fileReader = new BufferedReader(new InputStreamReader(fileUpload.getInputStream(), "UTF-8"));
	    csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
	    Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	    csvParser.close();

	    for (CSVRecord csvRecord : csvRecords) {
		System.out.println("---before: \n" + csvRecord);
	    }

	    // verify CSV file
	    List<CSVRecord> csvRecordsLst = StreamSupport.stream(csvRecords.spliterator(), false)
		    // 1. remove 1st record which is header
		    .filter(record -> record.get(0).startsWith("id") ? false : true)
		    // 2. remove commented records
		    .filter(record -> record.get(0).startsWith("#") ? false : true)
		    // 3. remove record if have any empty field
		    .filter(record -> CSVUtil.isRecordEmpty(record) ? false : true)
		    // 4. remove record if salary < 0
		    .filter(record -> CSVUtil.isRecordSalaryInvalid(record) ? false : true)
		    // 5. get filtered records
		    .collect(Collectors.toList());

	    // after pre-filtering
	    if (csvRecordsLst.size() == 0) {
		return new ResponseEntity<>(new UploadUsersResDTO(200, "nothing to updated!"), HttpStatus.OK);
	    }
	    // continue to verify duplicated records (with duplicate ID)
	    if (CSVUtil.isDuplicateRecordId(csvRecordsLst)) {
		return new ResponseEntity<>(new UploadUsersResDTO(400, "at least a row with duplicate id!"),
			HttpStatus.OK);
	    }
	    // verify duplicated records (with duplicate Login)
	    if (CSVUtil.isDuplicateRecordLoginField(csvRecordsLst)) {
		return new ResponseEntity<>(new UploadUsersResDTO(400, "at least a row with duplicate login field!"),
			HttpStatus.OK);
	    }
	    // verify if login value was exist in DB
	    for (CSVRecord record : csvRecordsLst) {
		List<User> dbUserLst = userDAO.findByLogin(record.get(1));

		if (dbUserLst != null && dbUserLst.size() > 0) {
		    return new ResponseEntity<>(
			    new UploadUsersResDTO(400, "a row with duplicated login field existed in DB!"),
			    HttpStatus.OK);
		}
	    }

	    // still need verify more things as date format in CSV, unknown fields
	    // but temporary skip these verifications

	    // save users to DB
	    userDAO.saveAll(CSVUtil.convertCSVRecordToUser(csvRecordsLst));
	    // after flush if save operation somehow failure, Spring JPA will auto roll back
	    // for us and throw exception.
	    userDAO.flush();

	    for (CSVRecord csvRecord : csvRecordsLst) {
		System.out.println("after====: \n" + csvRecord);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    // if you want to pass this exception to global exception then do:
	    // throw new GlobalUserManagementException("uploaded Failed !", 400);
	    return new ResponseEntity<>(new UploadUsersResDTO(400, "uploaded Failed !"), HttpStatus.OK);

	}

	return new ResponseEntity<>(new UploadUsersResDTO(201, "uploaded Successfully !"), HttpStatus.OK);
    }

    /**
     *
     */
    @Override
    public ResponseEntity<FetchUserResDTO> fetchUsers(Float minSalary, Float maxSalary, Integer offset, Integer limit) {

	// verify inputs
	if (minSalary < 0 || maxSalary < 0 || offset < 0 || limit < 0) {
	    return new ResponseEntity<>(new FetchUserResDTO(400, "Bad inputs!", null), HttpStatus.OK);
	}

	List<User> userLst = userDAO.fechUsers(minSalary, maxSalary);
	List<User> userLstWithOffset = null;

	if (userLst != null) {
	    // sort users by ID
	    userLst.sort((a, b) -> a.getId().compareTo(b.getId()));

	    userLstWithOffset = new ArrayList<>();

	    // take offset and limit
	    if (limit == 0) {
		for (int n = offset; n < userLst.size(); n++) {
		    userLstWithOffset.add(userLst.get(n));
		}
	    } else {
		for (int n = offset; n < userLst.size(); n++) {
		    if (limit > 0) {
			limit--;
			userLstWithOffset.add(userLst.get(n));
		    }
		}
	    }
	}

	if (userLstWithOffset.size() == 0) {
	    return new ResponseEntity<>(new FetchUserResDTO(200, "no user found!", userLstWithOffset), HttpStatus.OK);
	}

	return new ResponseEntity<>(new FetchUserResDTO(200, "Success!", userLstWithOffset), HttpStatus.OK);
    }

    /**
     *
     */
    @Override
    public ResponseEntity<GetUserResDTO> getUser(String userID) {
	User usr = userDAO.findById(userID).orElse(null);

	if (usr == null) {
	    return new ResponseEntity<>(new GetUserResDTO(400, "no user founds!", null), HttpStatus.OK);
	}

	return new ResponseEntity<>(new GetUserResDTO(200, "success", usr), HttpStatus.OK);
    }

    /**
     *
     */
    @Override
    public ResponseEntity<CreateUserResDTO> createUser(CreateUserReqDTO userReq) {
	User user = new User();

	// validate inputs before create new user
	if (CSVUtil.isSalaryInvalid(userReq.getSalary())) {
	    return new ResponseEntity<>(new CreateUserResDTO(400, "Invalid salary !"), HttpStatus.OK);
	}

	if (DateUtils.determineDateFormat(userReq.getStartDate()) == null) {
	    return new ResponseEntity<>(new CreateUserResDTO(400, "Invalid date !"), HttpStatus.OK);
	}

	User usr = userDAO.findById(userReq.getId()).orElse(null);
	if (usr != null) {
	    return new ResponseEntity<>(new CreateUserResDTO(400, "Employee ID already exists !"), HttpStatus.OK);
	}

	// verify login in DB
	List<User> dbUserLst = userDAO.findByLogin(userReq.getId());

	if (dbUserLst != null && dbUserLst.size() > 0) {
	    return new ResponseEntity<>(new CreateUserResDTO(400, "Employee login not unique!"), HttpStatus.OK);
	}

	try {
	    // if all passed
	    user.setId(userReq.getId());
	    user.setLogin(userReq.getLogin());
	    user.setName(userReq.getName());
	    user.setSalary(Float.valueOf(userReq.getSalary()));
	    user.setStartDate(DateUtils.localDateFromString(userReq.getStartDate()));

	    // save user
	    userDAO.save(user);
	    userDAO.flush();
	} catch (Exception e) {
	    return new ResponseEntity<>(new CreateUserResDTO(400, "internal error!"), HttpStatus.OK);
	}

	return new ResponseEntity<>(new CreateUserResDTO(200, "Successfully created!"), HttpStatus.OK);
    }

    /**
     *
     */
    @Override
    public ResponseEntity<UpdateUserResDTO> updateUser(String userID, String newSalary) {

	User usr = userDAO.findById(userID).orElse(null);
	if (usr == null) {
	    return new ResponseEntity<>(new UpdateUserResDTO(400, "Employee ID not exists !"), HttpStatus.OK);
	}

	try {
	    usr.setSalary(Float.valueOf(newSalary));
	    // save user
	    userDAO.save(usr);
	    userDAO.flush();
	} catch (Exception e) {
	    return new ResponseEntity<>(new UpdateUserResDTO(400, "failed to update user!"), HttpStatus.OK);
	}

	return new ResponseEntity<>(new UpdateUserResDTO(200, "updated Successfully !"), HttpStatus.OK);
    }

    /**
     *
     */
    @Override
    public ResponseEntity<UpdateUserResDTO> deletUser(String userID) {
	try {
	    // check user before delete
	    if (getUser(userID) == null) {
		return new ResponseEntity<>(new UpdateUserResDTO(400, "Bad input - no such employee !"), HttpStatus.OK);
	    }
	    // start delete
	    userDAO.deleteById(userID);
	    userDAO.flush();
	} catch (Exception e) {
	    return new ResponseEntity<>(new UpdateUserResDTO(400, "internal error !"), HttpStatus.OK);
	}

	return new ResponseEntity<>(new UpdateUserResDTO(200, "Successfully deleted!"), HttpStatus.OK);
    }

}
