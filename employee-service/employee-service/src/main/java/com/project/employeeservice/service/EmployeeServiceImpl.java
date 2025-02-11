package com.project.employeeservice.service;

import com.project.employeeservice.dto.APIResponseDto;
import com.project.employeeservice.dto.DepartmentDto;
import com.project.employeeservice.dto.EmployeeDto;
import com.project.employeeservice.entity.Employee;
import com.project.employeeservice.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository;
    RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Employee savedEmployee= employeeRepository.save(employee);
        EmployeeDto saveEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
        return saveEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate
                .getForEntity("http://localhost:8080/api/departments/getDepartment/"+employee.getDepartmentCode(),
                        DepartmentDto.class);
        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto,departmentDto);
        return apiResponseDto;
    }
}
