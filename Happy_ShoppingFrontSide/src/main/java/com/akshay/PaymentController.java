package com.akshay;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.akshay.DAO.CartDAO;
import com.akshay.DAO.OrderDAO;
import com.akshay.DAO.ProductDAO;
import com.akshay.DAO.UserDAO;
import com.akshay.model.CartItem;
import com.akshay.model.OrderDetail;
import com.akshay.model.UserDetail;

@Controller
public class PaymentController 
{
	@Autowired
	CartDAO cartDAO;


	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	OrderDAO orderDAO;
	
@RequestMapping("/Check Out")
public String checkout(Model m,HttpSession session)
{
	
	String username=(String)session.getAttribute("username");	    
    List<CartItem> cartItemList=cartDAO.listCartItem(username);
   
    m.addAttribute("cartItemList", cartItemList);
    m.addAttribute("grandTotal", this.getGrandTotal(cartItemList));
	UserDetail userDetail=userDAO.getUser(username);
   String address=userDAO.getUser(username).getCustomerAddr();
    m.addAttribute("addr", address);
    String saddress=userDAO.getUser(username).getShippingAddr();
    m.addAttribute("saddr", saddress);
    
    
	return "OrderConfirm";
}

@RequestMapping(value="/updateAddress",method=RequestMethod.POST)
public String updateAddress(@RequestParam("saddr")String saddr,Model m,HttpSession session )
{

	String username=(String)session.getAttribute("username");	    
    List<CartItem> cartItemList=cartDAO.listCartItem(username);
   
    m.addAttribute("cartItemList", cartItemList);
    m.addAttribute("grandTotal", this.getGrandTotal(cartItemList));
    
	UserDetail userDetail=userDAO.getUser(username);

	userDetail.setShippingAddr(saddr);
    userDAO.updateAddress(userDetail);

   String address=userDetail.getCustomerAddr();
   
    m.addAttribute("addr",address);
    String saddress=userDetail.getShippingAddr();
    m.addAttribute("saddr", saddress);
    		
	
	return "OrderConfirm";
}

@RequestMapping(value="/Payment")
public String PaymentPage(Model m,HttpSession session)
{
	
return "Payment";
	
}
	@RequestMapping(value="/Receipt",method=RequestMethod.POST)
	public String generateReceipt(@RequestParam ("Pmode")String Pmode,Model m,HttpSession session) 
	{
		String username=(String)session.getAttribute("username");

		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setOrderDate(new java.util.Date());
		orderDetail.setShippingAddr(userDAO.getUser(username).getShippingAddr());
		orderDetail.setTranType(Pmode);
		orderDetail.setUsername(username);
		
		
		   List<CartItem> cartItemList=cartDAO.listCartItem(username);
		   
		    m.addAttribute("cartItemList", cartItemList);
		    m.addAttribute("grandTotal", this.getGrandTotal(cartItemList));
		    
			UserDetail userDetail=userDAO.getUser(username);
		
		
		orderDetail.setTotalAmount(this.getGrandTotal(cartItemList));
		 String address=userDetail.getCustomerAddr();
		 String saddress=userDetail.getShippingAddr();
		m.addAttribute("addr",address);
		m.addAttribute("saddr", saddress);
		orderDAO.saveOrder(orderDetail);
		orderDAO.updateCart(username);
		m.addAttribute("orderDetail", orderDetail);
		
		
		
		
		return "Receipt";
	}
	
	
public int getGrandTotal(List<CartItem> cartList)
{
	 int grandTotal=0,count=0;
	 
	 
	 while(count<cartList.size())
	 {
		 grandTotal=grandTotal+(cartList.get(count).getQuantity()*cartList.get(count).getPrice());
		 count++;
	 }
	 
	 return grandTotal; 
}
}
