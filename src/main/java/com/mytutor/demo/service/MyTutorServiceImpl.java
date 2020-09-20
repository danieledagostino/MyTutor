package com.mytutor.demo.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mytutor.demo.model.Category;
import com.mytutor.demo.model.projection.CategoryProjection;
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
	
	public String purchase(String categoryName, Integer quantity) {
		
		Category category = repository.searchByName(categoryName);
		
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
			
			repository.save(category);
			
			return stockOk;
		}
	}
	
	public Integer checkStockForCategory(String categoryName) {
		Category c = repository.searchByName(categoryName);
		
		return c.getCopiesInStock();
	}
	
	public String report() {
		
		List<CategoryProjection> list = repository.report(supplierPrice);
		
		StringJoiner sj = new StringJoiner("\n");
		
		BigDecimal totalProfit = BigDecimal.ZERO;
		int i = 1;
		
		for (CategoryProjection c : list) {
			totalProfit = totalProfit.add(c.getCategoryProfit());
			
			sj.add(String.format("%d. Book %s | %s Copies Sold | £%.2f Total Profit", 
					i++, c.getName(), c.getCopiesSold(), c.getCategoryProfit()));
		}
		
		StringJoiner head = new StringJoiner("\n");
		head.add(String.format("MyTutor Bookshop Balance: £%.2f", totalProfit));
		return head.merge(sj).toString();
	}
}
