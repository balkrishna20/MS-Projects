package com.example.model;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.persistence.*;

/**
 * Created by Wayne.
 */

@Entity
@IdClass(OrderKey.class)
public class Orders {
	@Id
	@Column(name = "ordersid")
	public Integer ordersid;
	@Id
	@Column(name = "userid")
	public Integer userid;
	@Id
	@Column(name = "menuid")
	public Integer menuid;

	@Transient
	public String timeString;

	@Transient
	public String dateString;

	@Column(name = "time")
	public Time sqlTime;

	@Column(name = "date")
	public Date sqlDate;

	@Column(name = "quantity")
	public int quantity=1;

	@Column(name = "status")
	public String status;

	@Transient
	public OrderKey orderKey;

	@Transient
	public String convertDate;

	@Transient
	public String convertTime;

	public OrderKey getOrderKey() {
		return orderKey;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getordersid() {
		return ordersid;
	}

	public void setordersid(Integer ordersid) {
		this.ordersid = ordersid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getTime() {
		return timeString;
	}

	public void setTime(String timeString) {
		this.timeString = timeString;
	}

	public String getDate() {
		return dateString;
	}

	public void setDate(String dateString) {
		this.dateString = dateString;
	}


	public Time getSqlTime() {
		return sqlTime;
	}

	public void setSqlTime(Time sqlTime) {
		this.sqlTime = sqlTime;
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss", Locale.US);
		convertDate = timeFormat.format(sqlTime);

	}

	public Date getSqlDate() {
		return sqlDate;
	}

	public void setSqlDate(Date sqlDate) {
		this.sqlDate = sqlDate;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		convertDate = dateFormat.format(sqlDate);
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
