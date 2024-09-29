package com.knowprogram.demo.dao.impl;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import com.knowprogram.demo.dao.IStudentDao;
import com.knowprogram.demo.model.Student;

import jakarta.annotation.Resource;

@Repository
public class StudentDaoImpl implements IStudentDao {

	private final String KEY = "STUDENT";

	// inject template from AppConfig
	@Resource(name = "redisTemplate")
	private HashOperations<String, Integer, Student> operation;

	@Override
	public void addStudent(Student s) {
		// create new record in HashMemory if given id not exist
		operation.putIfAbsent(KEY, s.getStdId(), s);
	}

	@Override
	public void modifyStudent(Student s) {
		// update data with given id
		operation.put(KEY, s.getStdId(), s);
	}

	@Override
	public Student getOneStudent(Integer id) {
		// read one record based on HashRed and Id
		return operation.get(KEY, id);
	}

	@Override
	public Map<Integer, Student> getAllStudent() {
		// hash Ref, get all rows as Map
		return operation.entries(KEY);
	}

	@Override
	public void removeStudent(Integer id) {
		// Hash Red, key
		operation.delete(KEY, id);
	}

}
