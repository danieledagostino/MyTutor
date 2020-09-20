package com.mytutor.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.mytutor.demo.DemoApplication;
import com.mytutor.demo.service.MyTutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@TestMethodOrder(Alphanumeric.class)
public class MyTutorServiceTest {

	@Autowired
	MyTutorService service;

	@Value("${stock.ok}")
	private String stockOk;
	
	String firstReport = "MyTutor Bookshop Balance: £0,00\r\n" + 
			"1. Book A | 0 Copies Sold | £0,00 Total Profit\r\n" + 
			"2. Book B | 0 Copies Sold | £0,00 Total Profit\r\n" + 
			"3. Book C | 0 Copies Sold | £0,00 Total Profit\r\n" + 
			"4. Book D | 0 Copies Sold | £0,00 Total Profit\r\n" + 
			"5. Book E | 0 Copies Sold | £0,00 Total Profit";

	@Test
    public void A_testMyTutorServiceImplAutowired() {   
        assertNotNull(service);
    }
	
	@Test
	public void B_first_report() {

		String result = service.report();

		Assert.hasText(result, firstReport);
	}

	@Test
	public void C_purchase_A_1q() {

		String result = service.purchase("A", 1);

		assertEquals(result, stockOk);
	}
	
	@Test
	public void D_purchase_B_10q() {

		String result = service.purchase("A", 1);

		assertEquals(result, stockOk);
	}

}
