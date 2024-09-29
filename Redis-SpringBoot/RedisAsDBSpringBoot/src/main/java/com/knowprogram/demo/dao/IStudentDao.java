package com.knowprogram.demo.dao;

import java.util.Map;

import com.knowprogram.demo.model.Student;

public interface IStudentDao {
	
	void addStudent(Student s);
	void modifyStudent(Student s);
	Student getOneStudent(Integer id); 
	Map<Integer, Student> getAllStudent();
	void removeStudent(Integer id);
}
