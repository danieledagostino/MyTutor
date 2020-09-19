package com.mytutor.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mytutor.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query(value = "from Category where name like %:name%")
	public Category searchByName(@Param("name") String name);
	
}
