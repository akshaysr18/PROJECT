package com.akshay.DAO;
import java.util.List;

import com.akshay.model.category;
public interface categoryDAO
		{
		public boolean addcategory(category Category);
		public boolean deletecategory(category Category);
		public boolean updatecategory(category Category);
	public List<category> listcategories();
	public category getcategory(int CategoryID);
	}

