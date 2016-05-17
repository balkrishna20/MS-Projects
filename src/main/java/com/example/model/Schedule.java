package com.example.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "scheduleID")
	public Integer scheduleID;

	@Column(name = "chefID")
	public Integer chefID;

	@Column(name = "ordersid")
	public Integer ordersid;

	@Column(name = "userid")
	public Integer userid;

	@Column(name = "starttime")
	public Timestamp starttime;

	@Column(name = "endtime")
	public Timestamp endtime;



	public Integer getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(Integer scheduleID) {
		this.scheduleID = scheduleID;
	}

	public Integer getChefID() {
		return chefID;
	}

	public void setChefID(Integer chefID) {
		this.chefID = chefID;
	}

	public Timestamp getStartTime() {
		return starttime;
	}

	public void setStartTime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndTime() {
		return endtime;
	}

	public void setEndTime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Integer getOrdersid() {
		return ordersid;
	}

	public void setOrdersid(Integer ordersid) {
		this.ordersid = ordersid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}


}
