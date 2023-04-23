package com.buysellbid.buysellbid.model;

import java.sql.Date;

public class OrderItem {
	
	private String orderNo;
	private Date expShipDate;
	private Date actShipDate;
	private Date orderTime;
	private String buyerComment;
	private int buyerRating;
	private String selllerComment;
	private int sellerRating;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getExpShipDate() {
		return expShipDate;
	}
	public void setExpShipDate(Date expShipDate) {
		this.expShipDate = expShipDate;
	}
	public Date getActShipDate() {
		return actShipDate;
	}
	public void setActShipDate(Date actShipDate) {
		this.actShipDate = actShipDate;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getBuyerComment() {
		return buyerComment;
	}
	public void setBuyerComment(String buyerComment) {
		this.buyerComment = buyerComment;
	}
	public int getBuyerRating() {
		return buyerRating;
	}
	public void setBuyerRating(int buyerRating) {
		this.buyerRating = buyerRating;
	}
	public String getSelllerComment() {
		return selllerComment;
	}
	public void setSelllerComment(String selllerComment) {
		this.selllerComment = selllerComment;
	}
	public int getSellerRating() {
		return sellerRating;
	}
	public void setSellerRating(int sellerRating) {
		this.sellerRating = sellerRating;
	}
	
	
	

}
