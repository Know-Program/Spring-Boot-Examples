package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Employee", description = "Employee Management APIs")
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * POST - /emp
     * 
     * Save Employee
     * 
     * @param employee
     * @return
     */
    @Operation(summary = "Save Employee", description = "Create a new employee")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Employee created", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Employee.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    /**
     * PUT - /emp/{id}
     * 
     * Update Employee
     * 
     * @param employee
     * @return
     */
    @Operation(summary = "Update Employee", description = "Update an existing employee")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee updated", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Employee.class))),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable Long id) {
        return new ResponseEntity<>(employeeService.update(employee, id), HttpStatus.OK);
    }

    /**
     * GET - /emp
     * 
     * Get All Employees
     * 
     * @return
     */
    @Operation(summary = "Get All Employees", description = "Retrieve all employees")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employees retrieved", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Employee.class)))
    })
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    /**
     * GET - /emp/{id}
     * 
     * Get Employee by ID
     * 
     * @param id
     * @return
     */
    @Operation(summary = "Get Employee by ID", 
            description = "Retrieve an employee by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Employee retrieved", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Employee.class))),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    /**
     * DELETE - /emp/{id}
     * 
     * Delete Employee by ID
     * 
     * @param id
     * @return
     */
    @Operation(summary = "Delete Employee by ID", 
            description = "Delete an employee by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
