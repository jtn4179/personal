package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private double salary;
    private Date effectiveDate;
    private String employeeId;

    public Compensation() {}

    public double getSalary()
    {
        return this.salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public Date getEffectiveDate() 
    {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    public String getEmployeeId()
    {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
}
