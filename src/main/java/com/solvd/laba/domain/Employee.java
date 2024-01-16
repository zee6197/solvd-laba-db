package com.solvd.laba.domain;

import java.util.Date;
import java.util.HashMap;

public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private Date hireDate;
    private Double salary;
    private Credential credentials;



    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public Date getHireDate() {

        return hireDate;
    }

    public void setHireDate(Date hireDate) {

        this.hireDate = hireDate;
    }

    public Double getSalary() {

        return salary;
    }

    public void setSalary(Double salary) {

        this.salary = salary;
    }

    public Credential getCredentials() {

        return credentials;
    }

    public void setCredentials(Credential credentials) {

        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", credentials=" + (credentials != null ? credentials.toString() : null) +
                '}';
    }
}
