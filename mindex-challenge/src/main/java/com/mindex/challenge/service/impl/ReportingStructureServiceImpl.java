package com.mindex.challenge.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    // create employee controller to allow access for the implementation
    @Autowired
    private EmployeeController ec;

    @Override
    public ReportingStructure calcReports(String employeeId) {

        // find the employee to populate into the structure
        Employee employee = ec.read(employeeId);
        ReportingStructure structure = new ReportingStructure();
        structure.setEmployee(employee);

        // manage unchecked employees
        List<String> unchecked = new ArrayList<>();
        unchecked.add(employee.getEmployeeId());

        // manage checked employees
        Set<String> visited = new HashSet<>();
        visited.add(employee.getEmployeeId());

        // loop until all employees have been searched
        while (!unchecked.isEmpty())
        {
           // get current unchecked employee
           Employee current = ec.read(unchecked.get(0));
           List<Employee> underlings = current.getDirectReports();

           // if employee has direct reports
           if (underlings != null)
           {
                // handle each direct report
                for (Employee underling : underlings)
                {
                    // has it been checked already?
                    if (!visited.contains(underling.getEmployeeId()))
                    {
                        // not seen yet, make sure it gets checked
                        unchecked.add(underling.getEmployeeId());
                    }

                    // employee has been visited
                    visited.add(underling.getEmployeeId());
                }
           }
           
            // remove current employee from unchecked (it was just checked)
            unchecked.remove(0);
        }

        // end of loop, length of set = number of reports
        structure.setNumberOfReports(visited.size()-1); //subtract 1 to not count starting employee
        
        return structure;
    }
    
}
