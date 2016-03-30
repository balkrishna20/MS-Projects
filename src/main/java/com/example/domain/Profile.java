package com.example.domain;

/**
 * Created by rominoushana on 3/28/16.
 */
public class Profile {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String organization;
    private String aboutMyself;

    public Profile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

