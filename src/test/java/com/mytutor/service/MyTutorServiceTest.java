package com.mytutor.service;

import static org.junit.Assert.assertNotNull;
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

	@Test
    public void A_testMyTutorServiceImplAutowired() {   
        assertNotNull(service);
    }

	@Test
	public void B_purchase() {

		String result = service.purchase("A", 1);

		assertEquals(result, stockOk);
	}

}
