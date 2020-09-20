package com.mytutor.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mytutor.demo.model.Category;
import com.mytutor.demo.model.projection.CategoryProjection;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query(value = "from Category where name like %:name%")
	public Category searchByName(@Param("name") String name);
	
	@Query(value = "select c.name as name, c.copiesSold as copiesSold, c.price as price, "
			+ "((copiesSold * price) - ((copiesSold * price) * :supplierPrice)) as categoryProfit from Category c "
			+ "order by c.copiesSold, categoryProfit")
	List<CategoryProjection> report(@Param("supplierPrice") BigDecimal supplierPrice);
	
}
