package com.knowprogram.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowprogram.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
