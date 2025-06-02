package com.ra.controller;

import com.ra.model.entity.Employee;
import com.ra.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "home";
    }

    @GetMapping("/add-employee")
    public String add(Employee employee, Model model){
        model.addAttribute("employee", employee);
        return "add";
    }

    @PostMapping("/add-employee")
    public String create(@ModelAttribute Employee employee){
        if(employeeService.save(employee)!=null){
            return "redirect:/";
        }
        return "redirect:/add-employee?error=true";
    }

    @GetMapping("/edit-employee")
    public String edit(@RequestParam("id") Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "edit";
        }
        return "redirect:/employees?error=notfound";
    }

    @PostMapping("/edit-employee")
    public String update(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/change-employee-status")
    public String toggleStatus(@RequestParam("id") Integer id) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            employee.setStatus(!employee.getStatus());
            employeeService.save(employee);
        }
        return "redirect:/";
    }

}
