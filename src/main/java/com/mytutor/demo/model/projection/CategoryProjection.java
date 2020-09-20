package com.mytutor.demo.model.projection;

import java.math.BigDecimal;

public interface CategoryProjection {

	String getName();
	Integer getCopiesSold();
	BigDecimal getPrice();
	BigDecimal getCategoryProfit();
}
