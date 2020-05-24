package com.restapi.model;

public class Employee {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String jobPosition;

    public Employee(Integer id, String firstName, String lastName, String email, String jobPosition) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobPosition = jobPosition;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
