package com.buysellbid.buysellbid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BuySellBid {


	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement ps;

	public static void main(String[] args) {
		try {
			// connect to the database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/buybidsell"
					+ "", "root", "Yoksh@07");
			// create the statement and prepare the result set
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			while (true) {
				System.out.println("Select an option:");
				System.out.println("1. Display Top Seller");
				System.out.println("2. orderno whose buyerrating is 4");
				System.out.println("3. Certified refurbished items");
				System.out.println("4. seller who has availability more than desired quantity");
				System.out.println("5. top bidding amount");
				System.out.println("6. itemid available with its shipping type");
				System.out.println("7. Itemid with min(availability) greater than  desired avilability in descending order");
				System.out.println("8. Exit");

				Scanner sc = new Scanner(System.in);

				int option = sc.nextInt();

				if (option == 1) {
					Scanner scanner = new Scanner(System.in);
			        System.out.print("Enter rating: ");
			        int rating = scanner.nextInt();
					displayTopSeller(rating);
				} else if (option == 2) {
					Scanner scanner = new Scanner(System.in);
			        System.out.print("Enter rating: ");
			        int rating = scanner.nextInt();
					displayToOrderFromBuyer(rating);
				} else if (option == 3) {
					Scanner scanner=new Scanner(System.in);
					System.out.println("Enter condition for the product");
					String condition=scanner.nextLine();
					displayConditionOfProduct(condition);
				} else if (option == 4) {
					Scanner scanner=new Scanner(System.in);
					System.out.println("Enter Availability quantity");
					int quantity=scanner.nextInt();
					displayAvailabilityOfProduct(quantity);
				} else if (option == 5) {
					displayTopBidAmount();
				} else if (option == 6) {
					Scanner scanner=new Scanner(System.in);
					System.out.println("Enter the search term you want to filter with- ");
					String searchTerm=scanner.nextLine();
					displayFreeshippingProducts(searchTerm);
				} else if (option == 7) {
					Scanner scanner=new Scanner(System.in);
					int quantity=scanner.nextInt();
					displayAvailabilityGreaterThanTen(quantity);
				} else if (option == 8) {
					System.out.println("Exiting program...");
					break;
				} 
				else {
					System.out.println("Invalid option. Please try again.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// close the database connection
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void displayTopSeller(int rating) throws SQLException {
		System.out.println("SellerName with rating "+ rating + " is given below");
		String sql = "select  Isellername from orders t1 "
				+ "inner join Order_Item t2 on t1.OrderNo = t2.OrderNo\r\n"
				+ "where SellerRating = ? ;";
		
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		selectStatement.setInt(1, rating);
		ResultSet rs = selectStatement.executeQuery();
		
		// Display the results
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			System.out.println("-----------------");
			for (int i = 1; i <= numColumns; i++) {
				System.out.println(rs.getString(i) + " ");
			}
			System.out.println("-----------------");
		}
	}
	
	private static void displayToOrderFromBuyer(int rating) throws SQLException { 
		System.out.println("Order number for the buyer rating with " + rating + " is given below");
		String sql = "select  t1.OrderNo from orders t1\r\n"
				+ "inner join Order_Item t2 on t1.OrderNo = t2.OrderNo "
				+ "where BuyerRating =?;";
		PreparedStatement selectStatement=conn.prepareStatement(sql);
		selectStatement.setInt(1, rating);
		ResultSet rs = selectStatement.executeQuery();
		
		// Display the results
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= numColumns; i++) {
				System.out.println(rs.getString(i) + " ");
			}
			System.out.println("-------------------");
		}
	}
	
	private static void displayConditionOfProduct(String condition) throws SQLException {
		System.out.println("List of all products with the " + condition +" are given below ");
		String sql = "select * from Item\r\n"
				+ "where conditn = ? ;";
		
		PreparedStatement query=conn.prepareStatement(sql);
		query.setString(1, condition);
		ResultSet rs = query.executeQuery();
		// Display the results
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= numColumns; i++) 
			{
				System.out.println(rs.getString(i) + " ");
			}
			System.out.println("-------------------------");
		}
	}
	
	private static void displayAvailabilityOfProduct(int quantity) throws SQLException {
		System.out.println("seller who has availableqty more than  "+ quantity + " quantity are listed below");
		String sql = "select SellerName from Seller t1\r\n"
				+ "inner join orders t2 on t1.SellerName = t2.ISellerName\r\n"
				+ "inner join Item t3 on t3.ItemID = t2.ItemID\r\n"
				+ "where availableQty > ?;";
		PreparedStatement selectStatement=conn.prepareStatement(sql);
		selectStatement.setInt(1, quantity);
		ResultSet rs = selectStatement.executeQuery();
		// Display the results
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= numColumns; i++) {
				System.out.println(rs.getString(i) + " ");
			}
			System.out.println("----------------------------------");
		}
	}

	
	private static void displayTopBidAmount() throws SQLException {
		System.out.println("top bidding amount is given below");
		String sql = "	select Bidder,max(bidAmount) from Bids\r\n"
				+ "	group by Bidder\r\n"
				+ "	order by max(bidAmount) desc limit 1;";
		ResultSet rs = stmt.executeQuery(sql);
		// Display the results
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= numColumns; i++) {
				System.out.println(rs.getString(i) + " ");
			}
			System.out.println("----------------------");
		}
	}
		
	private static void displayFreeshippingProducts(String searchTerm) throws SQLException {
		System.out.println("Data is listed below with the "+ searchTerm + " is given below");
		String sql = "	select ItemID from Item\r\n"
				+ "	where ShippingType like ? ;";
		PreparedStatement query= conn.prepareStatement(sql);
		query.setString(1, "%" + searchTerm + "%");
		ResultSet rs = query.executeQuery();

		// Display the results
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= numColumns; i++) {
				System.out.print(rs.getString(i) + " ");
			}
			System.out.println("----------------------");
		}
	}	
	
	
	private static void displayAvailabilityGreaterThanTen(int quantity) throws SQLException {
		System.out.println("Itemid with min(availability) greater than " + quantity  + " avilability in descending order");
		String sql ="select ItemID,min(availableqty) as min_availableqty from Item\r\n"
				+ "	group by ItemID\r\n"
				+ "	having min(availableqty) > ?\r\n"
				+ "	order by min(availableqty) desc;";
		
		PreparedStatement query= conn.prepareStatement(sql);
		query.setInt(1, quantity);
		ResultSet rs=query.executeQuery();
		
		// Display the results
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = metaData.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= numColumns; i++) {
				System.out.print(rs.getString(i) + " ");
			}
			System.out.println("----------------------");
		}
	}

}
