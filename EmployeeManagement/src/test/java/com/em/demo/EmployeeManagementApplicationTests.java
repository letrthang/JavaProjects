package com.em.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.em.dto.CreateUserReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Also can use swagger for testing:
 * 
 * http://localhost:8080/swagger-ui.html
 * 
 * @author Thang Le
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class EmployeeManagementApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUserUsingRestTemplate() {
	// call restful to see result directly if you want. BUT you need to start your
	// app first then to run this test after that. it is not convenient way so it is
	// better to NOT using this method. Let's go with mockMvc

	/**
	 * RestTemplate restTemplate = new RestTemplate(); GetUserResDTO res =
	 * restTemplate.getForObject("http://localhost:8080/users/12345",
	 * GetUserResDTO.class); System.out.println("" + res.getMessage());
	 * assertEquals("success", res.getMessage());
	 */
    }

    @Test
    public void testCreateUser() throws Exception {
	CreateUserReqDTO usr = new CreateUserReqDTO();
	usr.setId("e0123");
	usr.setLogin("TL");
	usr.setName("Thang Le");
	usr.setSalary("101.01");
	usr.setStartDate("2020-12-31");

	ObjectMapper objectMapper = new ObjectMapper();
	String usrJson = objectMapper.writeValueAsString(usr);

	mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		.content(usrJson)).andExpect(jsonPath("$.message").value("Successfully created!"))
		.andExpect(jsonPath("$.responseStatus").value(200)).andExpect(status().isOk());
    }

    @Test
    public void testGetUser() throws Exception {
	// create user in DB first
	CreateUserReqDTO usr = new CreateUserReqDTO();
	usr.setId("e0123");
	usr.setLogin("TL");
	usr.setName("Thang Le");
	usr.setSalary("101.01");
	usr.setStartDate("2020-12-31");

	ObjectMapper objectMapper = new ObjectMapper();
	String usrJson = objectMapper.writeValueAsString(usr);

	mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		.content(usrJson)).andExpect(jsonPath("$.message").value("Successfully created!"))
		.andExpect(jsonPath("$.responseStatus").value(200)).andExpect(status().isOk());

	// then get it back
	mockMvc.perform(get("/users/e0123")).andExpect(jsonPath("$.message").value("success"))
		.andExpect(jsonPath("$.responseStatus").value(200)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetUserInvalid() throws Exception {
	mockMvc.perform(get("/users/e0123xx")).andExpect(jsonPath("$.message").value("no user founds!"))
		.andExpect(jsonPath("$.responseStatus").value(400)).andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
	// user id = 12345 was inserted from sql script during system starting
	mockMvc.perform(delete("/users/12345")).andExpect(jsonPath("$.message").value("Successfully deleted!"))
		.andExpect(jsonPath("$.responseStatus").value(200)).andExpect(status().isOk());
    }

    @Test
    public void testFetchUsers() throws Exception {

	// create user1 in DB
	CreateUserReqDTO usr = new CreateUserReqDTO();
	usr.setId("e0123");
	usr.setLogin("TL");
	usr.setName("Thang Le");
	usr.setSalary("300.01");
	usr.setStartDate("2020-12-31");

	ObjectMapper objectMapper = new ObjectMapper();
	String usrJson = objectMapper.writeValueAsString(usr);

	mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		.content(usrJson)).andExpect(jsonPath("$.message").value("Successfully created!"))
		.andExpect(jsonPath("$.responseStatus").value(200)).andExpect(status().isOk());

	// create user2 in DB
	CreateUserReqDTO usr2 = new CreateUserReqDTO();
	usr2.setId("e0456");
	usr2.setLogin("TL2");
	usr2.setName("Thang");
	usr2.setSalary("2000.01");
	usr2.setStartDate("2020-11-31");

	ObjectMapper objectMapper2 = new ObjectMapper();
	String usrJson2 = objectMapper2.writeValueAsString(usr2);

	mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
		.content(usrJson2)).andExpect(jsonPath("$.message").value("Successfully created!"))
		.andExpect(jsonPath("$.responseStatus").value(200)).andExpect(status().isOk());

	// then fetch them
	mockMvc.perform(get("/users").param("minSalary", "100").param("maxSalary", "3000").param("offset", "0")
		.param("limit", "2")).andExpect(jsonPath("$.message").value("Success!"))
		.andExpect(jsonPath("$.responseStatus").value(200)).andExpect(status().isOk())
		.andExpect(jsonPath("$.results.*", hasSize(2))).andExpect(content().contentType("application/json"))
		.andDo(print());
    }

}
