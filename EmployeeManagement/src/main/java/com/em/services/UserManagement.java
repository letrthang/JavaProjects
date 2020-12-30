package com.em.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.em.dto.CreateUserReqDTO;
import com.em.dto.CreateUserResDTO;
import com.em.dto.FetchUserResDTO;
import com.em.dto.GetUserResDTO;
import com.em.dto.UpdateUserResDTO;
import com.em.dto.UploadUsersResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/UserManagement")
public interface UserManagement {

    @ApiOperation(value = "File", notes = "upload users from CSV file")
    @PostMapping(path = "/users/upload", consumes = { "multipart/form-data" })
    @ResponseBody
    ResponseEntity<UploadUsersResDTO> uploadUsers(@RequestPart("file") MultipartFile fileUpload);

    @ApiOperation(value = "fetchUsers", notes = "fetchUsers with optional parameters")
    @GetMapping("/users")
    @ResponseBody
    ResponseEntity<FetchUserResDTO> fetchUsers(@RequestParam("minSalary") Float minSalary,
	    @RequestParam("maxSalary") Float maxSalary, @RequestParam("offset") Integer offset,
	    @RequestParam("limit") Integer limit);

    // ============ CRUD ==========
    // Get User
    @ApiOperation(value = "getUser", notes = "get user")
    @GetMapping("/users/{userID}")
    @ResponseBody
    ResponseEntity<GetUserResDTO> getUser(@PathVariable("userID") String userID);

    // Create User
    @ApiOperation(value = "createUser", notes = "create user")
    @PostMapping("/users")
    @ResponseBody
    ResponseEntity<CreateUserResDTO> createUser(@RequestBody CreateUserReqDTO userReq);

    // Update User
    @ApiOperation(value = "updateUser", notes = "update user salary")
    @RequestMapping(value = "/users/{userID}/{newSalary}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    @ResponseBody
    ResponseEntity<UpdateUserResDTO> updateUser(@PathVariable("userID") String userID,
	    @PathVariable("newSalary") String newSalary);

    // Delete User
    @ApiOperation(value = "deletUser", notes = "delete user")
    @DeleteMapping("/users/{userID}")
    @ResponseBody
    ResponseEntity<UpdateUserResDTO> deletUser(@PathVariable("userID") String userID);
}
