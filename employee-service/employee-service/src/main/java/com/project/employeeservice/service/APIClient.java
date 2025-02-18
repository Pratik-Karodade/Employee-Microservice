package com.project.employeeservice.service;

import com.project.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("/api/departments/getDepartment/{departmentCode}")
    DepartmentDto getDepartment(@PathVariable("departmentCode") String departmentCode);
}
