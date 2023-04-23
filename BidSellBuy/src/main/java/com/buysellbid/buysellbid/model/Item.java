package com.buysellbid.buysellbid.model;

public class Item {
	
	private String itemId;
	private String location;
	private String conditn;
	private String shippingType;
	private int availableQty;
	private String description;
	private String iSellerName;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getConditn() {
		return conditn;
	}
	public void setConditn(String conditn) {
		this.conditn = conditn;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getiSellerName() {
		return iSellerName;
	}
	public void setiSellerName(String iSellerName) {
		this.iSellerName = iSellerName;
	}
	
	

}
