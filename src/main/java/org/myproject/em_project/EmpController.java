package org.myproject.em_project;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

// @CrossOrigin("http://localhost:5173")
// @RequestMapping("/api/v1/") //--extra path
@RestController
public class EmpController {

    // List<Employee> employees = new ArrayList<>();
    // EmployeeService employeeService = new EmployeeServiceImp();

    // dependency injection --- IOC container hame eak object bna ke dega
    @Autowired
    EmployeeService employeeService;

    @GetMapping("get-allEmp")
    public List<Employee> getAllEmployees() {
        return employeeService.readEmployees();
    }

    @GetMapping("getEmp/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.readEmployee(id);
    }

    @PostMapping("post-empData")
    public String postMethodName(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return "saved successfully";
    }

    @DeleteMapping("delemp/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return "Delete Sucessfully";
        }
        return "not found";
    }

    @PutMapping("update/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id,employee);
        return "updated successfully";
    }

}
