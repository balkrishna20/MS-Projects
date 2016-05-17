package com.example.model;

import javax.persistence.*;

@Entity
public class Admin {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userid;

	@Column(name = "name")
    public String userName;
	@Column(name = "password")
    private String password;
	
	

    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


    public Integer getUserId(){
		return userid;
	}
	public void setUserId(Integer userid) {
		this.userid = userid;
	}
	
    public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}


}


