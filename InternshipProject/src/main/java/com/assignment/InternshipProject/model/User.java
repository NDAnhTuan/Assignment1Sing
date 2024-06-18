package com.assignment.InternshipProject.model;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime last_edited;
    private Boolean isLoggedIn;
    private LocalDate dateOfBirth;
    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.created_at = LocalDateTime.now();
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getLast_edited() {
        return last_edited;
    }

    public void setLast_edited(LocalDateTime last_edited) {
        this.last_edited = last_edited;
    }
    public void validate()
            throws ValidationException
    {
        validateFieldNotEmpty(userName, "User name");
        validateFieldNotEmpty(password, "Password");
        validatePasswordLength(password);
    }
    private void validateFieldNotEmpty(String field, String fieldName) throws ValidationException{
        if(field == null || field.trim().isEmpty()){
            throw new ValidationException(fieldName + " is mandatory");
        }
    }
    private void validatePasswordLength(String password) throws ValidationException{
        if(password == null || password.length() < 8){
            throw new ValidationException("Password should be 8 characters long minimum");
        }
    }



}
