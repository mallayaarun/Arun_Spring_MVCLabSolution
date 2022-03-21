package com.greatlearning.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.studentmanagement.entity.Student;
import com.greatlearning.studentmanagement.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController 
{
	
	@Autowired
	private StudentService studentservice;
	
	
	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> students = studentservice.findAll();
		model.addAttribute("Students", students);
		return "list-Students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "Student-form";
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String updateStudent(@RequestParam("studentId") int id, Model model) {
		Student student = new Student();
		
		student = studentservice.findById(id);
		model.addAttribute("Student", student);
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, 
			@RequestParam("firstName") String first, 
			@RequestParam("lastName") String last, 
			@RequestParam("course") String course, 
			@RequestParam("country") String country) {
		
		Student student;
		
		if(id!=0) {
			student = studentservice.findById(id);
			student.setFirstName(first);
			student.setLastName(last);
			student.setCourse(course);
			student.setCountry(country);
		} else {
			student = new Student(first, last, course, country );
		}
		studentservice.save(student);
		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentservice.deleteById(id);
		return "redirect:/student/list";
		
	}
}