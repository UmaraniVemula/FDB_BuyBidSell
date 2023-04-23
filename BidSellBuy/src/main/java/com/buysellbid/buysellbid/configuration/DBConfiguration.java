package com.buysellbid.buysellbid.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {

	Connection conn;
	
	@Bean
	public Connection myDBConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/buybidsell"
					+ "", "root", "Yoksh@07");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
