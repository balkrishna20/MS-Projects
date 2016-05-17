package com.example.model;

import javax.persistence.*;

/**
 * Created by rominoushana on 3/28/16.
 */
@Entity
public class Users {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "userid")
    public Integer ids;
   
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    public String password;   
    @Column(name = "verificationstatus",  nullable = false)
    public Boolean verificationstatus = false;  
    @Column(name = "code")
    public String code;

    @Transient
    public String inputCode;

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }



    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }


    public Boolean getActivationStatus() {
        return verificationstatus;
    }

    public void setActivationStatus(Boolean activationStatus) {
        this.verificationstatus = activationStatus;
    }

    public Integer getNumberID() {
        return ids;
    }

    public void setNumberID(Integer ids) {
        this.ids = ids;
    }
    
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

}

