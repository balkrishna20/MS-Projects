package com.example.model;


import javax.persistence.*;

/**
 * Created by Wayne
 */
@Entity
public class Admin {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userid;

	
//    public Integer userid;
	
	@Column(name = "name")
    public String name;
	@Column(name = "password")
    private String password;
	
	

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
//	public Integer getUserIds() {
//		return userids;
//	}
//	public void setUserIds(Integer userids) {
//		this.userids = userids;
//	}


//  
//  @Column(name="userids")
//  public String userids;
//
//  public String getUserids() {
//		return userids;
//	}
//
//	public void setUserids(String userids) {
//		this.userids = userids;
//	}
//	@Column(name = "name")
//  public String name;
	
//  public Integer getUserId() {
//  return userid;
//}
//
//public void setUserID(Integer userid) {
//  this.userid= userid;
//}
//public String getName() {
//	return name;
//}
//public void setName(String name) {
//	this.name = name;
//}

}


