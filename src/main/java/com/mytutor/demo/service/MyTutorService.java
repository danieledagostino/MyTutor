package com.mytutor.demo.service;

public interface MyTutorService {

	public String purchase(String categoryName, Integer quantity);
	
	public Integer checkStockForCategory(String categoryName);
	
	public String report();
}
