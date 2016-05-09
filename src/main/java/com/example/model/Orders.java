package com.example.model;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * Created by Wayne.
 */

@Entity 
public class Orders {
	
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ordersid")
	public Integer ordersid;
	@Column(name = "userid")
	public Integer userid;
	@Id
	@Column(name = "menuid")
	public Integer menuid;

	@Column(name = "time")
	public String time;

	@Column(name = "date")
	public String date;
	
	@Column(name = "quantity")
	public int quantity=1;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getOrdersid() {
		return ordersid;
	}

	public void setOrdersid(Integer ordersid) {
		this.ordersid = ordersid;
	}

	// private ArrayList<Orders> ordersList = new ArrayList<Orders>();
	// private ArrayList<Menuitems> menuitemsList = new ArrayList<Menuitems>();

	// public ArrayList<Orders> getOrdersList() {
	// return ordersList;
	// }
	//
	//
	//
	// public void setOrdersList(ArrayList<Orders> ordersList) {
	// this.ordersList = ordersList;
	// }

	public Integer getUserid() {
		return userid;
	}

	// public ArrayList<Menuitems> getMenuitemsList() {
	// return menuitemsList;
	// }
	//
	// public void setMenuitemsList(ArrayList<Menuitems> menuitemsList) {
	// this.menuitemsList = menuitemsList;
	// }

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
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
