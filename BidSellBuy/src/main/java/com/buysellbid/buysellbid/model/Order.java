package com.buysellbid.buysellbid.model;

public class Order {

	private String orderNo;
	private String itemID;
	private String isellerName;
	private String buyerName;
	private int quantity;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getIsellerName() {
		return isellerName;
	}
	public void setIsellerName(String isellerName) {
		this.isellerName = isellerName;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
