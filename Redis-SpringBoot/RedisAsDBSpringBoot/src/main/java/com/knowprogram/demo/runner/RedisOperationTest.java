package com.knowprogram.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.knowprogram.demo.dao.IStudentDao;
import com.knowprogram.demo.model.Student;

@Component
public class RedisOperationTest implements CommandLineRunner {

	@Autowired
	private IStudentDao dao;

	@Override
	public void run(String... args) throws Exception {
		dao.addStudent(new Student(101, "Amelia", 500.25));
		dao.addStudent(new Student(102, "William", 800.25));
		dao.addStudent(new Student(103, "Jerry", 600.25));

		System.out.println("All Rows: ");
		dao.getAllStudent().forEach((k, v) -> System.out.println(k + " - " + v));

		dao.removeStudent(101);
		dao.modifyStudent(new Student(103, "Tom", 650.25));
		
		System.out.println("After remove/modify: ");
		dao.getAllStudent().forEach((k, v) -> System.out.println(k + " - " + v));
	}

}
