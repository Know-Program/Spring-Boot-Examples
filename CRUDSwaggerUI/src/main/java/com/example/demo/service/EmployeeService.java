package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new BusinessException("Please Send Proper Name, It's Blank", HttpStatus.BAD_REQUEST);
        }
        try {
            return employeeRepository.save(employee);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Given Employee Name Is Null", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new BusinessException(
                    "Something went wrong in the Service layer while saving the data: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Employee> getAll() {
        List<Employee> empList = null;
        try {
            empList = employeeRepository.findAll();
        } catch (Exception e) {
            throw new BusinessException("Something went wrong while fetching all data",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return empList;
    }

    public Employee findById(Long id) {
        try {
            return employeeRepository.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new BusinessException(
                    "Given Employee ID Is Null. Please Send Some ID to Be Fetched: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            throw new BusinessException("Given Employee ID does not exist in DB: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    public void deleteById(Long id) {
        findById(id);
        employeeRepository.deleteById(id);
    }

    public Employee update(Employee employee, Long id) {
        Employee existingEmployee = findById(id);
        existingEmployee.setName(employee.getName());
        return employeeRepository.save(existingEmployee);
    }
}
