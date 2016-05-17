package com.example.model;

import javax.persistence.*;

/**
 * Created by rominoushana on 3/28/16.
 */
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer ids;
    @Column(name = "id")
    public String id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    public String address;
    @Column(name = "organization")
    private String organization;
    @Column(name = "aboutMyself")
    private String aboutMyself;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }



    public String getAboutMyself() {
        return aboutMyself;
    }

    public void setAboutMyself(String aboutMyself) {
        this.aboutMyself = aboutMyself;
    }



    public Integer getNumberID() {
        return ids;
    }

    public void setNumberID(Integer ids) {
        this.ids = ids;
    }
}

