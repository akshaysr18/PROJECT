package com.akshay.Happy_Shopping;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.akshay.DAO.OrderDAO;
import com.akshay.model.OrderDetail;

public class TestingOrder {

static OrderDAO orderDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.akshay");
		context.refresh();	
		orderDAO=(OrderDAO)context.getBean("OrderDAO");
	}
	
	@Test
	public void saveOrderTest()
	{
		OrderDetail orderDetail=new OrderDetail();
		
		orderDetail.setOrderDate(new java.util.Date());
		orderDetail.setCartId(101);
		orderDetail.setShippingAddr("Pubg Street");
		orderDetail.setTotalAmount(5000);
		orderDetail.setTranType("COD");
		orderDetail.setUsername("akshaysr");
		
		assertTrue("problem in Saving Order",orderDAO.saveOrder(orderDetail));
	}
	@Test
	public void updateCart()
	{
		assertTrue("problem in Updating Cart",orderDAO.updateCart("akshaysr"));
		
	}
	
}



