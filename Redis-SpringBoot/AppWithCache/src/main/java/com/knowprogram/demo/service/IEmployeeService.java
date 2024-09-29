package com.knowprogram.demo.service;

import java.util.List;

import com.knowprogram.demo.model.Employee;

public interface IEmployeeService {

    public Employee saveEmployee(Employee e);

    public Employee updateEmployee(Integer empId, Employee e);

    public void deleteEmployee(Integer empId);

    public Employee getOneEmployee(Integer empId);

    public List<Employee> getAllEmployees();
}
