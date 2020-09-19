package com.mytutor.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 2708405013478556804L;

	@Id
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private BigDecimal price;
	
	@Column
	private Integer copiesInStock;
	
	@Column
	private Integer copiesSold;
	
	@OneToMany(targetEntity = Book.class)
	private Set<Book> books;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Integer getCopiesInStock() {
		return copiesInStock;
	}

	public void setCopiesInStock(Integer copiesInStock) {
		this.copiesInStock = copiesInStock;
	}

	public Integer getCopiesSold() {
		return copiesSold;
	}

	public void setCopiesSold(Integer copiesSold) {
		this.copiesSold = copiesSold;
	}
	
}
