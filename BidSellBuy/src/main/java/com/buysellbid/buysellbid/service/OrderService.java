package com.buysellbid.buysellbid.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buysellbid.buysellbid.DAO.OrderDAO;
import com.buysellbid.buysellbid.model.Item;
import com.buysellbid.buysellbid.model.ItemAvailableQty;
import com.buysellbid.buysellbid.model.Order;
import com.buysellbid.buysellbid.model.OrderItem;

@Service
public class OrderService {
	
	@Autowired
	OrderDAO orderDao;
	
	public ArrayList<Order> displayTopSeller(int rating) throws SQLException {
		ArrayList<Order> arrayList = orderDao.getTopSeller(rating);
		
		System.out.println("displayTopSeller size : "+arrayList.size() +" for rating : "+rating);
		return arrayList;
	}
	
	public ArrayList<Order> displayTopBuyerOrder(int rating) throws SQLException {
		ArrayList<Order> arrayList = orderDao.getOrderNoByBuyerRating(rating);
		
		System.out.println("displayTopBuyerOrder size : "+arrayList.size() +" for rating : "+rating);
		return arrayList;
	}
	
	public ArrayList<Item> getCertifiedRefurbishedItems() throws SQLException {
		ArrayList<Item> arrayList = orderDao.getCertifiedRefurbishedItems("Certified refurbished");
		
		System.out.println("getCertifiedRefurbishedItems size : "+arrayList.size());
		return arrayList;
	}
	
	public ArrayList<String> getSellerByAvailableQty(int availableQty) throws SQLException {
		ArrayList<String> arrayList = orderDao.getSellerByAvailableQty(availableQty);
		
		System.out.println("getSellerByAvailableQty size : "+arrayList.size() +" for rating : "+availableQty);
		return arrayList;
	}
	
	public Map<String, Object> getTopBiddingAmount() throws SQLException {
		Map<String, Object> map = orderDao.getTopBiddingAmount();
		
		System.out.println("getTopBiddingAmount size : "+map.size());
		return map;
	}
	
	public ArrayList<Item> getItemShippingType() throws SQLException {
		ArrayList<Item> arrayList = orderDao.getItemShippingType("Free");
		
		System.out.println("getItemShippingType size : "+arrayList.size());
		return arrayList;
	}
	
	public List<ItemAvailableQty> getItemMinAvailableQty(int availableQty) throws SQLException {
		List<ItemAvailableQty> arrayList = orderDao.getItemMinAvailableQty(availableQty);
		
		System.out.println("getItemMinAvailableQty size : "+arrayList.size() +" for rating : "+availableQty);
		return arrayList;
	}
	
	public Order placeOrder(String itemId, String buyerName) {
		Item item = orderDao.getItemById(itemId);
		if(item == null) {
			return null;
		}
		Random random = new Random();
		OrderItem newOrderItem = new OrderItem();
		newOrderItem.setOrderNo(String.valueOf(random.nextInt()& Integer.MAX_VALUE));
		newOrderItem.setOrderTime(new java.sql.Date(System.currentTimeMillis()));
		newOrderItem.setExpShipDate(new java.sql.Date(System.currentTimeMillis()));
		newOrderItem.setActShipDate(new java.sql.Date(System.currentTimeMillis()));
		newOrderItem.setSellerRating(5);
		newOrderItem.setBuyerComment("Byer comment on Order "+ newOrderItem.getOrderNo());
		newOrderItem.setBuyerRating(5);
		newOrderItem.setSelllerComment("Seller comment on Order "+ newOrderItem.getOrderNo());
		String orderNo = orderDao.saveOrderItem(newOrderItem);
		
		if(orderNo == null) {
			return null;
		}
		Order order = new Order();
		order.setOrderNo(orderNo);
		order.setItemID(itemId);
		order.setBuyerName(buyerName);
		order.setIsellerName(item.getiSellerName());
		order.setQuantity(2);
		orderNo = orderDao.saveOrder(order);
		if(orderNo == null) {
			return null;
		}
		return order;
	}
}
