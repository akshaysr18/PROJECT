package com.akshay.Happy_Shopping;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.akshay.DAO.ProductDAO;
import com.akshay.model.Product;


public class TestingProduct 
{
static ProductDAO productDAO;

@BeforeClass
public static void executeFirst()
{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.akshay");
	context.refresh();
	productDAO=(ProductDAO)context.getBean("ProductDAO");
}

@Test
public void addProductTest()
{
	Product product=new Product();
	product.setProductDesc(" T-Shirt");
	product.setProductName("Lee ");
	product.setStock(25);
	product.setPrice(3999);
	product.setProductId(3);
	product.setCategoryId(4);
	product.setSupplierId(6);
	
	assertTrue("Problem in adding Product:",productDAO.addProduct(product));
}
@Ignore
@Test
public void deleteProductTest()
{
	Product product=productDAO.getProduct(1);
	assertTrue("Problem in Deleting Product:",productDAO.deleteProduct(product));
}
@Ignore
@Test
public void updateProductTest()
{
	Product product=productDAO.getProduct(3);
	product.setProductDesc("Pencil cut Jeans with Chain");
	assertTrue("Problem in Updating the Product",productDAO.updateProduct(product));
}
}
