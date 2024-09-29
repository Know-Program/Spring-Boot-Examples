package com.knowprogram.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowprogram.exception.EmployeeNotFoundException;
import com.knowprogram.model.Employee;
import com.knowprogram.repo.EmployeeRepository;
import com.knowprogram.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee saveEmployee(Employee e) {
		return repo.save(e);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not exist"));

		// Or, you can write it as,
		// Optional<Employee> opt = repo.findById(id);
		// if (opt.isPresent()) {
		// return opt.get();
		// }
		// throw new EmployeeNotFoundException("Employee not exist");
	}

	@Override
	public void deleteEmployee(Integer id) {
		Employee e = getOneEmployee(id);
		repo.delete(e);
	}
}
