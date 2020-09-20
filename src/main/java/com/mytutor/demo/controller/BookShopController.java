package com.mytutor.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytutor.demo.service.MyTutorService;

@RestController
@RequestMapping("/api/book")
public class BookShopController {

	@Autowired
    MyTutorService myTutorService;
	
	@PutMapping(value = "/purchase/{categoryName}/{quantity}")
    public ResponseEntity<String> purchase(@PathVariable(name = "categoryName") String categoryName,
    		@PathVariable(name = "quantity") Integer quantity) throws Exception
    {
    	
    	String response = myTutorService.purchase(categoryName, quantity);
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	@GetMapping(value = "/report")
    public ResponseEntity<String> report() throws Exception
    {
    	
    	String response = myTutorService.report();
    	
    	return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
