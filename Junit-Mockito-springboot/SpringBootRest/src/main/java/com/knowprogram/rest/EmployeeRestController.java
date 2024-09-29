package com.knowprogram.rest;

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

import com.knowprogram.model.Employee;
import com.knowprogram.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		Integer empId = service.saveEmployee(employee).getEmpId();
		return ResponseEntity.ok("Employee saved " + empId);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employees = service.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable Integer id) {
		Employee emp = service.getOneEmployee(id);
		return ResponseEntity.ok(emp);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
		service.deleteEmployee(id);
		return ResponseEntity.ok("Employee Deleted");
	}

	@PutMapping("/modify/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
		Employee emp = service.getOneEmployee(id);
		emp.setEmpName(employee.getEmpName());
		emp.setEmpSal(employee.getEmpSal());
		service.saveEmployee(employee);
		return ResponseEntity.ok("Employee Updated!!");
	}
}
