package com.akshay;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.akshay.DAO.ProductDAO;
import com.akshay.DAO.UserDAO;
import com.akshay.model.Product;
import com.akshay.model.UserDetail;

@Controller
public class UserController 
{
	@Autowired 
	ProductDAO productDAO;
	
	@Autowired
	UserDAO userDAO;
	static int i=0;

	@RequestMapping("/Home")
	public String showhome(Model m)
	{
		i=0;
		m.addAttribute("pageinfo", "Home");
		return "Home";
	
	}
	@RequestMapping("/logout")
	public String logout(Model m)
	{
		i=0;
		m.addAttribute("ErrorMessage","You have logged Out");
		return "login";
	}
	
	@RequestMapping("/login")
	public String showLogin(Model m)
	{
		
		if(i>0)
		{
		m.addAttribute("ErrorMessage", "Username or Password Invalid");	
	i=0;
		}
		else
		{
			i=0;
			m.addAttribute("ErrorMessage","Enter login details");
	
		}
		i++;
		return "login";
	}
	
	@RequestMapping(value="/UserHome")
	public String showUserHome(Model m,HttpSession session)
	{
		 i=0;

		m.addAttribute("ErrorMessage", "    ");
		m.addAttribute("pageinfo", "Product Catalog");
		List<Product> listProducts=productDAO.listProducts();
		m.addAttribute("productList", listProducts);
		
		return "UserHome";
	}
	
	@RequestMapping(value="/login_success")
	public String loginCheck(Model m,HttpSession session)
	{
		String page="";
		boolean loggedIn=false;
		
		SecurityContext securityContext=SecurityContextHolder.getContext();
		Authentication authentication=securityContext.getAuthentication();
		
		String username=authentication.getName();
		
		Collection<GrantedAuthority> roles=(Collection<GrantedAuthority>)authentication.getAuthorities();
	
		for(GrantedAuthority role:roles)
		{
			session.setAttribute("role", role.getAuthority());
			
			if(role.getAuthority().equals("Admin"))
			{
				loggedIn=true;
				page="AdminHome";
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("username", username);
			}
			else
			{
				m.addAttribute("pageinfo", "User Home");
				List<Product> listProducts=productDAO.listProducts();
				m.addAttribute("productList", listProducts);
				
				loggedIn=true;
				page="UserHome";
				session.setAttribute("loggedIn", loggedIn);
				session.setAttribute("username", username);
			}
		}
		
		
		return page;
	}
	@RequestMapping("/Register")
	public String showRegister(@ModelAttribute("UserDetail")UserDetail User,Model m)
	{
		m.addAttribute("pageinfo","Register");
		m.addAttribute("UserDetail", User);
		return "Register";
	}
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public String registerUser(Model m,@ModelAttribute("UserDetail")UserDetail User,@RequestParam("mobileNumber")String mobileNumber,@RequestParam("shippingAddr")String shippingAddr,@RequestParam("customerName")String customerName,@RequestParam("username")String username,@RequestParam("customerAddr")String customerAddr,@RequestParam("password")String password,@RequestParam("role")String role,@RequestParam("enabled")boolean enabled)
	{
		m.addAttribute("UserDetail", User);
		User.setCustomerAddr(customerAddr);
		User.setCustomerName(customerName);
		User.setEnabled(true);
		User.setPassword(password);
		User.setRole("User");
		User.setUsername(username);
	    User.setShippingAddr(shippingAddr);
	    User.setMobileNumber(mobileNumber);
		userDAO.registerUser(User);
		m.addAttribute("pageinfo","Register");
		return "RegisterSuccess";
	}
}
