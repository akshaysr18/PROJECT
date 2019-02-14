package com.akshay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akshay.DAO.categoryDAO;
import com.akshay.model.category;

@Controller
public class CategoryController 
{
	@Autowired
	categoryDAO categoryDAO;
	
	@RequestMapping("/Category")
public ModelAndView showcategory()
		{
		ModelAndView m = new ModelAndView();
		List<category> listcategories =categoryDAO.listcategories();
		m.addObject("listcategories", listcategories);
		m.setViewName("Category");
		return m;
		
		}

@PostMapping(value="/Addcategory")
public String Addcategory(Model m, @RequestParam("catName")String CategoryName,@RequestParam("catDesc")String CategoryDesc)
		{
	category category=new category();
	category.setCategoryName(CategoryName);
	category.setCategoryDesc(CategoryDesc);
	categoryDAO.addcategory(category); 
	List<category>listcategories=categoryDAO.listcategories();
	m.addAttribute("listcategories", listcategories);
	
	
	m.addAttribute("pageinfo", "Manage Category ");
return "Category";
		}

@PostMapping(value="UpdateCategory")
public String UpdateCategory(Model m, @RequestParam("catId")int CategoryId,  @RequestParam("catName")String CategoryName,@RequestParam("catDesc")String CategoryDesc)
		{
	category category=categoryDAO.getcategory(CategoryId);
	category.setCategoryName(CategoryName);
	category.setCategoryDesc(CategoryDesc);
	categoryDAO.updatecategory(category);
	
	List<category>listcategories=categoryDAO.listcategories();
	m.addAttribute("listcategories", listcategories);
	
	
	m.addAttribute("pageinfo", "Manage Category ");
	return "redirect:/Category";
	 	}


@RequestMapping(value="/deletecategory/{categoryId}")
public String deletecategory(Model m, @PathVariable("categoryId")int categoryId)
{
	category category=categoryDAO.getcategory(categoryId);
	categoryDAO.deletecategory(category);
	
	List<category>listcategories=categoryDAO.listcategories();
	m.addAttribute("listcategories", listcategories);
	
	m.addAttribute("pageinfo", "Manage Category");
	return "redirect:/Category";
}
@RequestMapping(value="/editCategory/{categoryId}")
public String editCategory(Model m, @PathVariable("categoryId")int categoryId)
{
	category category=categoryDAO.getcategory(categoryId);
	m.addAttribute("category", category);
	
	m.addAttribute("pageinfo", "Manage Category");
	return "UpdateCategory";
}
}

	