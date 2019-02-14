package com.akshay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.akshay.DAO.SupplierDAO;
import com.akshay.model.Supplier;

@Controller
public class SupplierController 
{
	@Autowired
	SupplierDAO supplierDAO;
	
	@RequestMapping("/Supplier")
public ModelAndView showSupplier()
		{
		ModelAndView m = new ModelAndView();
		List<Supplier> listSuppliers =supplierDAO.listSuppliers();
		m.addObject("listSuppliers", listSuppliers);
		m.setViewName("Supplier");
		return m;
		
		}

@PostMapping(value="/AddSupplier")
public String AddSupplier(Model m, @RequestParam("SupName")String SupplierName,@RequestParam("SupAddr")String SupplierAddr)
		{
	Supplier supplier=new Supplier();
	supplier.setSupplierName(SupplierName);
	supplier.setSupplierAddr(SupplierAddr);
	supplierDAO.addSupplier(supplier); 
	List<Supplier>listSuppliers=supplierDAO.listSuppliers();
	m.addAttribute("listSuppliers", listSuppliers);
	
	
	m.addAttribute("pageinfo", "Supplier");
return "Supplier";
		}

@PostMapping(value="UpdateSupplier")
public String UpdateSupplier(Model m, @RequestParam("SupId")int SupplierId,  @RequestParam("SupName")String SupplierName,@RequestParam("SupAddr")String SupplierAddr)
		{
	Supplier supplier=supplierDAO.getSupplier(SupplierId);
	supplier.setSupplierName(SupplierName);
	supplier.setSupplierAddr(SupplierAddr);
	supplierDAO.updateSupplier(supplier);
	
	List<Supplier>listSuppliers=supplierDAO.listSuppliers();
	m.addAttribute("listSuppliers", listSuppliers);
	
	
	m.addAttribute("pageinfo", "Manage Supplier ");
	return "redirect:/Supplier";
	 	}


@RequestMapping(value="/deleteSupplier/{SupplierId}")
public String deleteSupplier(Model m, @PathVariable("SupplierId")int SupplierId)
{
	Supplier supplier=supplierDAO.getSupplier(SupplierId);
	supplierDAO.deleteSupplier(supplier);
	
	List<Supplier>listSuppliers=supplierDAO.listSuppliers();
	m.addAttribute("listSuppliers", listSuppliers);
	
	m.addAttribute("pageinfo", "Manage Supplier");
	return "redirect:/Supplier";
}
@RequestMapping(value="/editSupplier/{supplierId}")
public String editSupplier(Model m, @PathVariable("supplierId")int SupplierId)
{
	Supplier supplier=supplierDAO.getSupplier(SupplierId);
	m.addAttribute("Supplier", supplier);
	
	m.addAttribute("pageinfo", "Manage Supplier");
	return "UpdateSupplier";
}
}
