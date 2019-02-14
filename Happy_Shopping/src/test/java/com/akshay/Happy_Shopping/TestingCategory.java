package com.akshay.Happy_Shopping;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.akshay.DAO.categoryDAO;
import com.akshay.model.category;

public class TestingCategory
{
	static categoryDAO categoryDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.akshay");
		context.refresh();
		categoryDAO=(categoryDAO)context.getBean("categoryDAO");
	}
	
	@Test
	public void addcategoryTest()
	{
		category Category = new category();
		Category.setCategoryName("T-Shirt");
		Category.setCategoryDesc("V-Neck T-Shirt,  [Half seleve] ");
		assertTrue("Problem in adding Category",categoryDAO.addcategory(Category));
	}
	@Ignore
	@Test
	public void deleteCategoryTest()
	{
		category Category=categoryDAO.getcategory(0);
		assertTrue("Problem in Deleting Category:",categoryDAO.deletecategory(Category));
	}
	@Ignore
	@Test
	public void updateCategoryTest()
	{
		category category=categoryDAO.getcategory(4);
		category.setCategoryDesc("Pencil cut Jeans with Chain");
		assertTrue("Problem in Updating the Category",categoryDAO.updatecategory(category));
	}
}



