package com.mytutor.demo.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mytutor.demo.model.Category;
import com.mytutor.demo.repository.CategoryRepository;

@Service
@Transactional
public class MyTutorServiceImpl implements MyTutorService {

	@Autowired
	CategoryRepository repository;
	
	@Value("${supplier.price}")
	private BigDecimal supplierPrice;
	
	@Value("${copies.limit}")
	private Integer copiesLimit;
	
	@Value("${more.copies}")
	private Integer moreCopies;
	
	@Value("${stock.not.exist}")
	private String stockNotExist;
	
	@Value("${stock.out.of}")
	private String stockOutOf;
	
	@Value("${stock.ok}")
	private String stockOk;
	
	public String purchase(String typeName, Integer quantity) {
		
		Category category = repository.searchByName(typeName);
		
		if (category == null) {
			return stockNotExist;
		}else if (category.getCopiesInStock() < quantity) {
			return stockOutOf;
		}else {
			category.setCopiesInStock(category.getCopiesInStock() - quantity);
			category.setCopiesSold(category.getCopiesSold() + quantity);
			if (category.getCopiesInStock() < copiesLimit) {
				category.setCopiesInStock(category.getCopiesInStock() + moreCopies);
			}
			
			return stockOk;
		}
		
	}
}
