package org.myproject.em_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // List<Employee> employees = new ArrayList<>();

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        // employees.add(employee);
        return "saved Successfully";
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> empList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : empList) {
            Employee emp = new Employee();
            BeanUtils.copyProperties(employeeEntity, emp);
            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        // employees.remove(id);
        employeeRepository.deleteById(id);
        return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {

        EmployeeEntity existingEmp = employeeRepository.findById(id).get();

        existingEmp.setName(employee.getName());
        existingEmp.setEmail(employee.getEmail());
        existingEmp.setPhone(employee.getPhone());

        // EmployeeEntity existingEmp = employeeRepository.findById(id)
        // .orElseThrow(() -> new RuntimeException("Employee not found"));

        // BeanUtils.copyProperties(employee, existingEmp, "id"); // ignore id
        employeeRepository.save(existingEmp);
        return "saved Successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity empEn = employeeRepository.findById(id).get();
        Employee emp = new Employee();
        BeanUtils.copyProperties(empEn, emp);
        return emp;
    }

}
