package com.project.employeeservice.service;

import com.project.employeeservice.dto.APIResponseDto;
import com.project.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    public EmployeeDto saveEmployee(EmployeeDto employeeDto);
    public APIResponseDto getEmployeeById(Long id);
}
