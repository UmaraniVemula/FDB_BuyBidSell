package com.buysellbid.buysellbid.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buysellbid.buysellbid.model.Item;
import com.buysellbid.buysellbid.model.ItemAvailableQty;
import com.buysellbid.buysellbid.model.Order;
import com.buysellbid.buysellbid.model.OrderItem;
import com.buysellbid.buysellbid.service.OrderService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@GetMapping(path = "/displayTopSeller/{rating}")
	public ResponseEntity<ArrayList> displayTopSeller(@PathVariable int rating) {
		ArrayList<Order> arrayList = null;
		try {
			arrayList = orderService.displayTopSeller(rating);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList>(arrayList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/displayTopBuyerOrder/{rating}")
	public ResponseEntity<ArrayList> displayTopBuyerOrder(@PathVariable int rating) {
		ArrayList<Order> arrayList = null;
		try {
			arrayList = orderService.displayTopBuyerOrder(rating);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList>(arrayList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getCertifiedRefurbishedItems")
	public ResponseEntity<ArrayList> getCertifiedRefurbishedItems() {
		ArrayList<Item> arrayList = null;
		try {
			arrayList = orderService.getCertifiedRefurbishedItems();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList>(arrayList, HttpStatus.OK);
	}

	@GetMapping(path = "/getSellerByAvailableQty/{availableQty}")
	public ResponseEntity<ArrayList> getSellerByAvailableQty(@PathVariable int availableQty) {
		ArrayList<String> arrayList = null;
		try {
			arrayList = orderService.getSellerByAvailableQty(availableQty);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList>(arrayList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getTopBiddingAmount")
	public ResponseEntity<Map<String, Object>> getTopBiddingAmount() {
		Map<String, Object> map = null;
		try {
			map = orderService.getTopBiddingAmount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getItemShippingType")
	public ResponseEntity<ArrayList> getItemShippingType() {
		ArrayList<Item> arrayList = null;
		try {
			arrayList = orderService.getItemShippingType();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList>(arrayList, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getItemMinAvailableQty/{availableQty}")
	public ResponseEntity<List> getItemMinAvailableQty(@PathVariable int availableQty) {
		List<ItemAvailableQty> arrayList = null;
		try {
			arrayList = orderService.getItemMinAvailableQty(availableQty);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(arrayList, HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/placeOrder")
	public ResponseEntity<Object> placeOrder(@RequestBody Map<String, Object> input){
		Order order = orderService.placeOrder((String)input.get("itemId"), (String)input.get("buyerName"));
		if(order == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	return new ResponseEntity<>(order, HttpStatus.OK);
		
	}
}
