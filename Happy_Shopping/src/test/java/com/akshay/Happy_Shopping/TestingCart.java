package com.akshay.Happy_Shopping;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.akshay.DAO.CartDAO;
import com.akshay.model.CartItem;

public class TestingCart {

static CartDAO cartDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.akshay");
		context.refresh();	
		cartDAO=(CartDAO)context.getBean("cartDAO");
	}
@Ignore
	@Test
	public void addCartTest() 
	{
	CartItem cartItem=new CartItem();
	cartItem.setProductId(142);
	cartItem.setProductName("Jogger jean");
	cartItem.setQuantity(23);
	cartItem.setPrice(1234);
	cartItem.setPaymentStatus("NP");
	cartItem.setUsername("akshaysr");
	assertTrue("problem in Adding into Cart",cartDAO.addCartItem(cartItem));
		
     }

@Ignore
@Test
public void updateCartItemTest()
{
CartItem cartItem=cartDAO.getCartItem(45);
cartItem.setQuantity(61);
assertTrue("problem in Upading the Cart",cartDAO.updateCartItem(cartItem));

} 
@Ignore
@Test
public void deleteCartItemTest()
{
	CartItem cartItem=cartDAO.getCartItem(45);
	assertTrue("problem in deleting the CartItem",cartDAO.deleteCartItem(cartItem));


}


	@Test
	public void displayCartItems()
	{
		List<CartItem> listCartItems=cartDAO.listCartItem("akshaysr");
		
		assertTrue("problem in Displaying the CartItems",listCartItems.size()>0);
		
		for(CartItem cartItem:listCartItems)
		{
			System.out.print(cartItem.getProductId()+"\t");
			System.out.print(cartItem.getProductName()+"\t");
			System.out.print(cartItem.getPrice()+"\t");
			System.out.println(cartItem.getPrice()*cartItem.getQuantity());
		}
		
	}
	
}
