package com.buysellbid.buysellbid.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buysellbid.buysellbid.model.Item;
import com.buysellbid.buysellbid.model.ItemAvailableQty;
import com.buysellbid.buysellbid.model.Order;
import com.buysellbid.buysellbid.model.OrderItem;

import jakarta.persistence.Entity;

@Repository
public class OrderDAO {

	
	  @Autowired Connection conn;
	  
		public ArrayList<Order> getTopSeller(int rating) {
			
			ArrayList<Order> arrayList = new ArrayList<>();
			String sql = "select  t1.* from orders t1 "
					+ "inner join Order_Item t2 on t1.OrderNo = t2.OrderNo\r\n" + "where SellerRating = ? ;";
	  
			ResultSet rs = null;
			try {
				PreparedStatement selectStatement = conn.prepareStatement(sql);
				selectStatement.setInt(1, rating);
				rs = selectStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  
			//	   Display the results 
			try {
				ResultSetMetaData metaData = rs.getMetaData();
				int numColumns = metaData.getColumnCount();
				while (rs.next()) {
					Order order= new Order();
					order.setOrderNo(rs.getString(1));
					order.setItemID(rs.getString(2));
					order.setIsellerName(rs.getString(3));
					order.setBuyerName(rs.getString(4));
					order.setQuantity(rs.getInt(5));
					arrayList.add(order);
				}
			} catch (SQLException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			return arrayList;
		}
	 
		
public ArrayList<Order> getOrderNoByBuyerRating(int rating) {
			
			ArrayList<Order> arrayList = new ArrayList<>();
			String sql = "select  t1.* from orders t1\r\n"
					+ "inner join Order_Item t2 on t1.OrderNo = t2.OrderNo\r\n"
					+ "where BuyerRating = ? ;";
	  
			ResultSet rs = null;
			try {
				PreparedStatement selectStatement = conn.prepareStatement(sql);
				selectStatement.setInt(1, rating);
				rs = selectStatement.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  
			//	   Display the results 
			try {
				
				while (rs.next()) {
					Order order= new Order();
					order.setOrderNo(rs.getString(1));
					order.setItemID(rs.getString(2));
					order.setIsellerName(rs.getString(3));
					order.setBuyerName(rs.getString(4));
					order.setQuantity(rs.getInt(5));
					arrayList.add(order);
				}
			} catch (SQLException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			return arrayList;
		}


public ArrayList<Item> getCertifiedRefurbishedItems(String rating) {
	
	ArrayList<Item> arrayList = new ArrayList<>();
	String sql = "select * from Item where conditn  = ? ;";

	ResultSet rs = null;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		selectStatement.setString(1, rating);
		rs = selectStatement.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	//	   Display the results 
	try {
		while (rs.next()) {
			Item item = new Item();
			
				item.setItemId(rs.getString(1));
				
				item.setItemId(rs.getString(1));
				item.setLocation(rs.getString(2));
				item.setConditn(rs.getString(3));
				item.setShippingType(rs.getString(4));
				item.setAvailableQty(rs.getInt(5));
				item.setDescription(rs.getString(6));
				item.setiSellerName(rs.getString(7));
			
				arrayList.add(item);
		}
	} catch (SQLException e) { // TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<String> getSellerByAvailableQty(int availableQty) {
	
	ArrayList<String> arrayList = new ArrayList<String>();
	String sql = "select SellerName from Seller t1\r\n"
			+ "inner join orders t2 on t1.SellerName = t2.ISellerName\r\n"
			+ "inner join Item t3 on t3.ItemID = t2.ItemID\r\n"
			+ "where availableQty > ? ;";

	ResultSet rs = null;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		selectStatement.setInt(1, availableQty);
		rs = selectStatement.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	//	   Display the results 
	try {
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= numColumns; i++) {
				arrayList.add(rs.getString(i));
			}
			System.out.println();
		}
	} catch (SQLException e) { // TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arrayList;
}

public Map<String, Object> getTopBiddingAmount() {
	
	Map<String, Object> map = new HashMap<>();
	String sql = "select Bidder,max(bidAmount) from Bids\r\n"
			+ "group by Bidder\r\n"
			+ "order by max(bidAmount) desc limit 1 ;";

	ResultSet rs = null;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		rs = selectStatement.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	//	   Display the results 
	try {
		while (rs.next()) {
			map.put("bidder", rs.getString(1));
		map.put("bidAmount", rs.getInt(2));
		}
	} catch (SQLException e) { // TODO Auto-generated catch block
		e.printStackTrace();
	}
	return map;
}

public ArrayList<Item> getItemShippingType(String shippingType) {
	
	ArrayList<Item> arrayList = new ArrayList<Item>();
	String sql = "select * from Item\r\n"
			+ "where ShippingType like '%Free%' ;";

	ResultSet rs = null;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
//		selectStatement.setString(1, shippingType);
		rs = selectStatement.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

//	   Display the results 
	try {
		while (rs.next()) {
			Item item = new Item();
				item.setItemId(rs.getString(1));
				item.setLocation(rs.getString(2));
				item.setConditn(rs.getString(3));
				item.setShippingType(rs.getString(4));
				item.setAvailableQty(rs.getInt(5));
				item.setDescription(rs.getString(6));
				item.setiSellerName(rs.getString(7));
			
				arrayList.add(item);
		}
	} catch (SQLException e) { // TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return arrayList;
}

public List<ItemAvailableQty> getItemMinAvailableQty(int availableQty) {
	
	List<ItemAvailableQty> list = new ArrayList<>();
	String sql = "select ItemID,min(availableqty) as min_availableqty from Item\r\n"
			+ "group by ItemID\r\n"
			+ "having min(availableqty) >= ? \r\n"
			+ "order by min(availableqty) desc;";

	ResultSet rs = null;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		selectStatement.setInt(1, availableQty);
		rs = selectStatement.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	//	   Display the results 
	try {
		while (rs.next()) {
			ItemAvailableQty itemAvailableQty = new ItemAvailableQty();
			itemAvailableQty.setItemId(rs.getString(1));
		itemAvailableQty.setAvailableQty(rs.getInt(2));
		list.add(itemAvailableQty);
		}
	} catch (SQLException e) { // TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}


public Item getItemById(String itemId) {
	
	String sql = "select * from Item where ItemID  = ? ;";

	ResultSet rs = null;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		selectStatement.setString(1, itemId);
		rs = selectStatement.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	//	   Display the results 
	try {
		while (rs.next()) {
			Item item = new Item();
			
				item.setItemId(rs.getString(1));
				
				item.setItemId(rs.getString(1));
				item.setLocation(rs.getString(2));
				item.setConditn(rs.getString(3));
				item.setShippingType(rs.getString(4));
				item.setAvailableQty(rs.getInt(5));
				item.setDescription(rs.getString(6));
				item.setiSellerName(rs.getString(7));
			
				return item;
		}
	} catch (SQLException e) { // TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

public String saveOrderItem(OrderItem orderItem) {
	String sql = "insert into Order_Item(OrderNo,ExpShipDate,ActShipDate,OrderTime,BuyerRating,BuyerComment,SellerRating,SellerComment) values('"+orderItem.getOrderNo()+"','"
		+orderItem.getExpShipDate()+"','" +orderItem.getActShipDate()+"','"
			+ orderItem.getOrderTime()+"','"+ orderItem.getBuyerRating() +"','"
		+ orderItem.getBuyerComment()+"','"+ orderItem.getSellerRating()+"','"
			+orderItem.getSelllerComment()+ "');";
	System.out.println(sql);
	int result=0;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		result = selectStatement.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 if (result > 0) {
         System.out.println("successfully inserted");
         return orderItem.getOrderNo();
        	
	 }
     else
         System.out.println(
             "unsucessful insertion ");
	return null;
}

public String saveOrder(Order order) {
	String sql = "insert into Orders(OrderNo,ItemID,ISellerName,BuyerName,Quantity) values('"+order.getOrderNo()+"',"
			+ "'"+order.getItemID()+"','"+order.getIsellerName()+"','"+order.getBuyerName()+"','"+order.getQuantity()+"');";
	int result=0;
	try {
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		result = selectStatement.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 if (result > 0) {
         System.out.println("successfully inserted");
         return order.getOrderNo();
        	
	 }
     else
         System.out.println(
             "unsucessful insertion ");
	return null;
}

}
