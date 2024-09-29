package com.knowprogram.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowprogram.demo.model.Employee;
import com.knowprogram.demo.service.impl.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAlleEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/one/{id}")
    public Employee getOneEmployee(@PathVariable("id") Integer empId) {
        return service.getOneEmployee(empId);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable("id") Integer empId, @RequestBody Employee employee) {
        return service.updateEmployee(empId, employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer empId) {
        service.deleteEmployee(empId);
        return "Employee " + empId + " deleted";
    }
}
