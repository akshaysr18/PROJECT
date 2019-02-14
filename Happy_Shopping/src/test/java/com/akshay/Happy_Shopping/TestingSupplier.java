package com.akshay.Happy_Shopping;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.akshay.DAO.SupplierDAO;
import com.akshay.model.Supplier;

public class TestingSupplier 
{
	static SupplierDAO supplierDAO;
	
	@BeforeClass
	public static void executeFirst() 
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.akshay");
		context.refresh();
supplierDAO=(SupplierDAO)context.getBean("supplierDAO");
	}
	
	@Test
	public void addSupplierTest()
	{
		Supplier supplier=new Supplier();
	    supplier.setSupplierName("DON");
	    supplier.setSupplierId(100);
	    supplier.setSupplierAddr("14/271,Chicken Dinner House, PUBG Street ,kerala,  kochi-5");
	    
	    assertTrue("Problem in adding Supplier:",supplierDAO.addSupplier(supplier));
	}
	@Ignore
	@Test
	public void deleteSupplierTest()
	{
		Supplier supplier=supplierDAO.getSupplier(2);
		assertTrue("Problem in deleting Supplier:",supplierDAO.deleteSupplier(supplier));
		
	}
	@Ignore
	@Test
	public void updateSupplierTest()
	{
		Supplier supplier=supplierDAO.getSupplier(144);
		supplier.setSupplierName("Akshay");
		assertTrue("Problem in updating Supplier:",supplierDAO.updateSupplier(supplier));
	}
	
	

}
