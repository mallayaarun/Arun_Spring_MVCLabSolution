package com.greatlearning.studentmanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.greatlearning.studentmanagement.entity.Student;

@Service
public interface StudentService {
	
	public List<Student> findAll();
	public void save(Student student);
	public void deleteById(int id);	
	public Student findById(int id);

}