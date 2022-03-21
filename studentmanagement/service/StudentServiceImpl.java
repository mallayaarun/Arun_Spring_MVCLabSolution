package com.greatlearning.studentmanagement.service;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.greatlearning.studentmanagement.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{

	
	private SessionFactory sessionfactory;
	
	private Session session;
	
	@Autowired
	public StudentServiceImpl(SessionFactory sessionfactory) {
		super();
		this.sessionfactory = sessionfactory;
		try {
		session=sessionfactory.getCurrentSession();
		}
		catch (HibernateException e) {
			session = sessionfactory.openSession();
		}
		
	}
	
	@Transactional
	@Override
	public List<Student> findAll() {
			
		Transaction transaction = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();
		transaction.commit();

		return students;
	}
	
	@Override
	public void save(Student student) {
		Transaction transaction = session.beginTransaction();

		session.saveOrUpdate(student);
		transaction.commit();
			
	}


	@Override
	public void deleteById(int id) {
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class,id);
		session.delete(student);
		transaction.commit();
		
	}

	@Override
	public Student findById(int id) {
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		transaction.commit();
		return student;
	}
}