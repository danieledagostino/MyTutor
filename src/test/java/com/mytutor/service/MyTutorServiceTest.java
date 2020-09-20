package com.mytutor.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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
	
	@Value("${stock.out.of}")
	private String outOfStock;

	@Value("${stock.ok}")
	private String stockOk;
	
	String B_report = "MyTutor Bookshop Balance: £0,00\n" + 
			"1. Book A | 0 Copies Sold | £0,00 Total Profit\n" + 
			"2. Book B | 0 Copies Sold | £0,00 Total Profit\n" + 
			"3. Book C | 0 Copies Sold | £0,00 Total Profit\n" + 
			"4. Book D | 0 Copies Sold | £0,00 Total Profit\n" + 
			"5. Book E | 0 Copies Sold | £0,00 Total Profit";
	
	String F_report = "MyTutor Bookshop Balance: £102,00\n" + 
			"1. Book B | 10 Copies Sold | £60,00 Total Profit\n" + 
			"2. Book C | 5 Copies Sold | £34,50 Total Profit\n" + 
			"3. Book A | 1 Copies Sold | £7,50 Total Profit\n" + 
			"4. Book D | 0 Copies Sold | £0,00 Total Profit\n" + 
			"5. Book E | 0 Copies Sold | £0,00 Total Profit";
	
	String I_report = "MyTutor Bookshop Balance: £147,00\n" + 
			"1. Book B | 10 Copies Sold | £60,00 Total Profit\n" + 
			"2. Book D | 5 Copies Sold | £45,00 Total Profit\n" + 
			"3. Book C | 5 Copies Sold | £34,50 Total Profit\n" + 
			"4. Book A | 1 Copies Sold | £7,50 Total Profit\n" + 
			"5. Book E | 0 Copies Sold | £0,00 Total Profit";

	@Test
    public void A_testMyTutorServiceImplAutowired() {   
        assertNotNull(service);
    }
	
	@Test
	public void B_first_report() {

		String result = service.report();

		assertTrue(result.equals(B_report));
	}

	@Test
	public void C_purchase_A_1q() {

		String result = service.purchase("A", 1);

		assertEquals(result, stockOk);
	}
	
	@Test
	public void D_purchase_B_10q() {

		String result = service.purchase("B", 10);

		assertEquals(result, stockOk);
	}
	
	@Test
	public void E_purchase_C_5q() {

		String result = service.purchase("C", 5);

		assertEquals(result, stockOk);
	}
	
	@Test
	public void F_2nd_report() {

		String result = service.report();

		assertTrue(result.equals(F_report));
	}
	
	@Test
	public void G_purchase_quantity_not_available() {

		String result = service.purchase("C", 10);

		assertEquals(result, outOfStock);
	}
	
	@Test
	public void H_purchase_D_5q() {

		String result = service.purchase("D", 5);

		Assert.hasText(result, outOfStock);
	}
	
	@Test
	public void I_3rd_report() {

		String result = service.report();

		assertTrue(result.equals(I_report));
	}

}
