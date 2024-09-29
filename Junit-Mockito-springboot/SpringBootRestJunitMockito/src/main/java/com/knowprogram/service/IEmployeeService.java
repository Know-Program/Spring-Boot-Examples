package com.knowprogram.service;

import java.util.List;

import com.knowprogram.model.Employee;

public interface IEmployeeService {

	public Employee saveEmployee(Employee e);

	public List<Employee> getAllEmployees();

	public Employee getOneEmployee(Integer id);

	public void deleteEmployee(Integer id);
}
