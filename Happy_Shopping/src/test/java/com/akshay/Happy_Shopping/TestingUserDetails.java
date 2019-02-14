package com.akshay.Happy_Shopping;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.akshay.DAO.UserDAO;
import com.akshay.model.UserDetail;

public class TestingUserDetails {
	
	static UserDAO userDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.scan("com.akshay");
		context.refresh();
		userDAO=(UserDAO)context.getBean("UserDAO");
	}

	@Test
	public void registerUserDetailtest() 
	{
		UserDetail user=new UserDetail();
		user.setUsername("akshay");
		user.setPassword("1234");
		user.setCustomerAddr("Aki/271, PUBG Street , Kochi");
		user.setCustomerName("Don");
		user.setEnabled(true);
		user.setRole("User");
		user.setMobileNumber("7012371260");
		user.setShippingAddr("Idduki");
		assertTrue("Problem in adding User:",userDAO.registerUser(user));
		
		
	}

}
