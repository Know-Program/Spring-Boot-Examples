package com.knowprogram.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowprogram.demo.exception.ResourceNotFoundException;
import com.knowprogram.demo.model.Employee;
import com.knowprogram.demo.repo.EmployeeRepository;
import com.knowprogram.demo.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public Employee saveEmployee(Employee e) {
        return repo.save(e);
    }

    @Override
    public Employee updateEmployee(Integer empId, Employee e) {
        Employee emp = repo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist"));
        emp.setEmpName(e.getEmpName());
        emp.setEmpSal(e.getEmpSal());
        return repo.save(emp);
    }

    @Override
    public void deleteEmployee(Integer empId) {
        Employee emp = repo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist"));
        repo.delete(emp);

    }

    @Override
    public Employee getOneEmployee(Integer empId) {
        Employee emp = repo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist"));
        return emp;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

}
