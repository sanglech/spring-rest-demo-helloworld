package com.christian.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.christian.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	//define endpoint for /students - return list of all students
	private List<Student> theStudents;
	
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Christian","Sangle"));
		theStudents.add(new Student("Super","Mario"));
		theStudents.add(new Student("Princess","Toadstool"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	//define endpoint for /students/{studentid} return student at index
	@GetMapping("/students/{studentid}")
	public Student getStudent(@PathVariable int studentid) {
		
		//just index into list.. keep it simple
		
		//check the student id 
		
		if(studentid>=theStudents.size()||studentid<0) {
			throw new StudentNotFoundException("Student id not found: "+ studentid);
		}
		
		return theStudents.get(studentid);
	}
		
}
