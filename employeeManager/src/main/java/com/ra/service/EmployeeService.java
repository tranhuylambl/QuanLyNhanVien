package com.ra.service;

import com.ra.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee save(Employee employee);
    Employee findById(Integer id);
    void delete(Integer id);
}
