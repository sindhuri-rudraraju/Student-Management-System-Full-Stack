package com.spring.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot.exception.ResourceNotFoundException;
import com.spring.springboot.model.Student;
import com.spring.springboot.repository.StudentRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	// get all students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	
	// create student rest api
		@PostMapping("/students")
		public Student createEmployee(@RequestBody Student student) {
			return studentRepository.save(student);
		}
		
		// get student by id rest api
		@GetMapping("/students/{id}")
		public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
			return ResponseEntity.ok(student);
		}
		
		// update student rest api
		
		@PutMapping("/students/{id}")
		public ResponseEntity<Student> updateEmployee(@PathVariable Long id, @RequestBody Student studentDetails){
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
			
			student.setFirstName(studentDetails.getFirstName());
			student.setLastName(studentDetails.getLastName());
			student.setEmailId(studentDetails.getEmailId());
			
			Student updatedStudent = studentRepository.save(student);
			return ResponseEntity.ok(updatedStudent);
		}
		
		// delete employee rest api
		@DeleteMapping("/students/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
			
			studentRepository.delete(student);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);

}
}
