package com.em.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import com.em.model.User;

/**
 * utility for string processing. try to use Apache string lib as possible
 * before you create a new util here
 *
 */
public class CSVUtil {

    private CSVUtil() {
    }

    /**
     * 
     * @param record
     * @return
     */
    public static boolean isRecordEmpty(CSVRecord record) {
	boolean ret = false;
	// total 5 fields to verify
	for (int n = 0; n < 5; n++) {
	    if (StringUtils.isBlank(record.get(n))) {
		ret = true;
		break;
	    }
	}

	return ret;
    }

    /**
     * 
     * @param record
     * @return
     */
    public static boolean isRecordSalaryInvalid(CSVRecord record) {
	boolean ret = false;

	try {
	    // column 3 = salary
	    if (Float.valueOf(record.get(3)) < 0) {
		ret = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    ret = true;
	}
	return ret;
    }

    /**
     * 
     * @param record
     * @return
     */
    public static boolean isSalaryInvalid(String salary) {
	boolean ret = false;

	try {
	    if (Float.valueOf(salary) < 0) {
		ret = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    ret = true;
	}
	return ret;
    }

    /**
     * 
     * @param recordLst
     * @return
     */
    public static boolean isDuplicateRecordId(Iterable<CSVRecord> recordLst) {
	boolean ret = false;

	Set<String> ids = new HashSet<>();
	for (CSVRecord Record : recordLst) {
	    if (ids.contains(Record.get(0))) {
		ret = true;
		break;
	    } else {
		ids.add(Record.get(0));
	    }
	}

	return ret;
    }

    /**
     * 
     * @param recordLst
     * @return
     */
    public static boolean isDuplicateRecordLoginField(Iterable<CSVRecord> recordLst) {
	boolean ret = false;

	Set<String> ids = new HashSet<>();
	for (CSVRecord Record : recordLst) {
	    if (ids.contains(Record.get(1))) {
		ret = true;
		break;
	    } else {
		ids.add(Record.get(0));
	    }
	}

	return ret;
    }

    /**
     * 
     * @param recordLst
     * @return
     */
    public static Set<User> convertCSVRecordToUser(Iterable<CSVRecord> recordLst) {

	Set<User> userLst = new HashSet<>();

	for (CSVRecord Record : recordLst) {
	    User usr = new User();
	    usr.setId(Record.get(0));
	    usr.setLogin(Record.get(1));
	    usr.setName(Record.get(2));
	    usr.setSalary(Float.valueOf(Record.get(3)));
	    usr.setStartDate(DateUtils.localDateFromString(Record.get(4)));

	    userLst.add(usr);
	}

	return userLst;
    }
}
