package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="orderDetails")
public class orderDetails {

	private int orderId, userID;
	private String txnID, amount, status = "pending";
	
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public orderDetails(){}

	@Id
	@GenericGenerator(name = "increment", strategy = "increment")
	@GeneratedValue(generator = "increment")
	public int getOrderID() {
		return orderId;
	}

	public void setOrderID(int orderId) {
		this.orderId = orderId;
	}

	@Column(length=1000)
	public String getTxnID() {
		return txnID;
	}

	public void setTxnID(String txnid) {
		this.txnID = txnid;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	


	
}
