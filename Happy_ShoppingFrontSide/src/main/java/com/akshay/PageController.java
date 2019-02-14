package com.akshay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController 
{


	
	
	@RequestMapping("/Contact Us")
	public String showcontactus(Model m)
	{
		m.addAttribute("pageinfo", "Contact Us");
		return "Contact Us";
	}
	
	@RequestMapping("/About Us")
	public String showaboutus(Model m)
	{
		m.addAttribute("pageinfo", "About Us");
		return "About Us";
	}
	
}
