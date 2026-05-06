package org.myproject.em_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    // ↑ Mockito creates a FAKE repository — no real database used

    @InjectMocks
    private EmployeeServiceImp employeeService;
    // ↑ Injects the fake repository into the real service

    @Test
    void createEmployee_ShouldSaveAndReturnSuccess() {
        // ARRANGE — set up the input
        Employee employee = new Employee();
        employee.setName("Bikash");
        employee.setPhone("9876543210");
        employee.setEmail("bikash@example.com");

        // ACT — call the actual method
        String result = employeeService.createEmployee(employee);

        // ASSERT — check it did the right thing
        assertEquals("saved Successfully", result);
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
        // ↑ confirms repository.save() was actually called once
    }

    @Test
    void readEmployees_ShouldReturnEmptyListWhenNoEmployees() {
        // ARRANGE — fake repo returns empty list
        when(employeeRepository.findAll()).thenReturn(java.util.Collections.emptyList());

        // ACT
        var result = employeeService.readEmployees();

        // ASSERT
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void deleteEmployee_ShouldCallRepositoryDelete() {
        // ARRANGE
        Long empId = 1L;
        doNothing().when(employeeRepository).deleteById(empId);

        // ACT
        employeeService.deleteEmployee(empId);

        // ASSERT — verify delete was called with correct ID
        verify(employeeRepository, times(1)).deleteById(empId);
    }
}