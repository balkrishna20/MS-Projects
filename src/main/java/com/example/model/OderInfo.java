package com.example.model;

import java.util.Date;

public class OderInfo {
	int userId;
	int duration;
	int orderId;
	Date finishBefore;
	
	public OderInfo(int userId, int duration, int orderId, Date finishBefore) {
		super();
		this.userId = userId;
		this.duration = duration;
		this.orderId = orderId;
		this.finishBefore = finishBefore;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getFinishBefore() {
		return finishBefore;
	}
	public void setFinishBefore(Date finishBefore) {
		this.finishBefore = finishBefore;
	}
}
