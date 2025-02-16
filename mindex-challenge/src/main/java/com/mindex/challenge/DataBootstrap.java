package com.mindex.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class DataBootstrap {
    private static final String EMPLOYEE_DATASTORE_LOCATION = "/static/employee_database.json";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        InputStream employeeInputStream = this.getClass().getResourceAsStream(EMPLOYEE_DATASTORE_LOCATION);

        Employee[] employees = null;

        try {
            employees = objectMapper.readValue(employeeInputStream, Employee[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Employee employee : employees) {
            employeeRepository.insert(employee);
        }
    }
}
