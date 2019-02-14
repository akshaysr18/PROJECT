package com.akshay;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.akshay.DAO.ProductDAO;
import com.akshay.DAO.SupplierDAO;
import com.akshay.DAO.categoryDAO;
import com.akshay.model.Product;
import com.akshay.model.Supplier;
import com.akshay.model.category;

@Controller
public class ProductController 
{
@Autowired
categoryDAO categoryDAO;

@Autowired
ProductDAO productDAO;

@Autowired
SupplierDAO supplierDAO;

@RequestMapping("/Product")
public String showProduct(Model m)
{
	Product product = new Product();
    m.addAttribute(product);
	
	List<Product> listProducts = productDAO.listProducts();
	m.addAttribute("productList", listProducts);
	m.addAttribute("categoryList",this.getCategories());
	m.addAttribute("SupplierList",this.getSuppliers());
	m.addAttribute("pageinfo", "Manage Product");
	return "Product";
	
}
@PostMapping(value="/InsertProduct")
public String InsertProduct(@ModelAttribute("product")Product product, @RequestParam("pimage")MultipartFile filedetail,Model m)
{
	productDAO.addProduct(product);
	
	Product product1 = new Product();
	m.addAttribute(product1);
	m.addAttribute("pageinfo" , "Manage Product");
	m.addAttribute("categoryList", this.getCategories());
	m.addAttribute("SupplierList",this.getSuppliers());
	String imagepath="D:\\project\\Happy_ShoppingFrontSide\\src\\main\\webapp\\resources\\images\\";
	imagepath=imagepath+String.valueOf(product.getProductId())+".jpg";
	
	File image=new File(imagepath);
	
	if(!filedetail.isEmpty())
	{
		try
		{
			byte buff[]=filedetail.getBytes();
			FileOutputStream fos=new FileOutputStream(image);
			BufferedOutputStream bs=new BufferedOutputStream(fos);
			bs.write(buff);
			bs.close();
		}
		catch(Exception e)
		{
			m.addAttribute("errorInfo", "Exception occured during uploading:"+e.getMessage());
		}
		}
	else
	{
		
		m.addAttribute("errorInfo","Problem occured during Uploading:");
	}

	
	List<Product> listProducts = productDAO.listProducts();
	m.addAttribute("productList", listProducts);
	
	return "Product";
	
}

@PostMapping(value="editProduct/UpdateProduct")
public String UpdateProduct(@ModelAttribute("product")Product product, Model m)
{
	productDAO.updateProduct(product);
	
	Product product1 = new Product();
	m.addAttribute(product1);
	m.addAttribute("pageinfo" , "Manage Product");
	m.addAttribute("categoryList", this.getCategories());
	m.addAttribute("SupplierList",this.getSuppliers());
	
	
	List<Product> listProducts = productDAO.listProducts();
	m.addAttribute("productList", listProducts);
	
	return "redirect:/Product";
	

	
}

@RequestMapping("/ProductDisplay")
public String displayAllProduct(@ModelAttribute("product")Product product, Model m)
{
	m.addAttribute("pageinfo" , "Product Catalog" );
	List<Product> listProducts = productDAO.listProducts();
	m.addAttribute("productList", listProducts);
	
	
	return "ProductDisplay";
}

@RequestMapping(value="/TotalProductDisplay/{productId}")
public String totalProductDisplay(@PathVariable("productId")int productId , Model m)
{
	m.addAttribute("pageinfo", "Product Info");
	Product product = productDAO.getProduct(productId);
	m.addAttribute("product", product);
	
	return "TotalProductDisplay";
}

public LinkedHashMap<Integer,String> getCategories()
{
	List<category> listcategories=categoryDAO.listcategories();
	LinkedHashMap<Integer,String> categoryList=new LinkedHashMap<Integer,String>();
	for(category category:listcategories)
	{
		categoryList.put(category.getCategoryId(), category.getCategoryName());
	}
		return categoryList;	
}
public LinkedHashMap<Integer,String> getSuppliers()
{
	List<Supplier> listSuppliers=supplierDAO.listSuppliers();
	LinkedHashMap<Integer,String> SupplierList=new LinkedHashMap<Integer,String>();
	for(Supplier Supplier:listSuppliers)
	{
		SupplierList.put(Supplier.getSupplierId(), Supplier.getSupplierName());
	}
		return SupplierList;	
}

@RequestMapping(value="/deleteProduct/{productId}")
public String deleteProduct(@PathVariable("productId")int productId,Model m)
{
	Product product = productDAO.getProduct(productId);
	productDAO.deleteProduct(product);
	
	Product product1 = new Product();
	m.addAttribute(product1);
	
	m.addAttribute("pageinfo" , "Manage Product");
	m.addAttribute("categoryList", this.getCategories());
	m.addAttribute("SupplierList",this.getSuppliers());
	
	List<Product> listProducts = productDAO.listProducts();
	m.addAttribute("productList", listProducts);
	
	return "redirect:/Product";
	
}

@RequestMapping(value="/editProduct/{productId}")
public String editProduct(@PathVariable("productId")int productId,Model m)
{
	Product product = productDAO.getProduct(productId);
	m.addAttribute("product", product);
	
	m.addAttribute("pageinfo", "Manage Product");
	m.addAttribute("categoryList", this.getCategories());
	m.addAttribute("SupplierList",this.getSuppliers());
	return "UpdateProduct";

}
}
