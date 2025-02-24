package com.indus.training.core.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.indus.training.persist.entity.Student;

import junit.framework.TestCase;

public class TestStudentDaoController extends TestCase {

	private final String BASE_URL = "http://localhost:9090/IndusStudentDaoRestService/rest/student";
	private RestTemplate restTemplate;

	protected void setUp() throws Exception {
		restTemplate = new RestTemplate();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInsertStudent() {
		Student student = new Student();
		student.setStudentId(190);
		student.setFirstName("Navya");
		student.setLastName("Bade");

		HttpEntity<Student> request = new HttpEntity<>(student);
		ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + "/insert", request, String.class);
		assertEquals("Student inserted successfully.", response.getBody());

	}
	
	
	public void testFetchStudent_Success() {
      
        ResponseEntity<Student> response = restTemplate.getForEntity(BASE_URL+ "/fetch/1026", Student.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(Integer.valueOf(1026), response.getBody().getStudentId());
        assertEquals("Teja", response.getBody().getFirstName());
    }
	
	
	public void testDeleteStudent_Success() throws Exception {
        // Assume a student with ID 190 exists
        ResponseEntity<String> response = restTemplate.exchange(BASE_URL + "/delete/190", HttpMethod.DELETE, null, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Student deleted successfully.", response.getBody());
    }
	
	public void testUpdateStudent_Success() throws Exception {
        // Assume a student with ID 190 exists and we are updating the first name
        String url = BASE_URL + "/update/1?firstName=NavyaUpdated";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Student updated successfully.", response.getBody());
    }


}
