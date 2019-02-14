package com.akshay.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.akshay.model.category;

@Repository("categoryDAO")
@Transactional
public class categoryDAOImpl implements categoryDAO
{
@Autowired
SessionFactory sessionFactory;

	@Override
	public boolean addcategory(category Category) {

		try
		{
			sessionFactory.getCurrentSession().save(Category);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean deletecategory(category Category) {

		try
		{
			sessionFactory.getCurrentSession().delete(Category);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public boolean updatecategory(category Category) {

		try
		{
			sessionFactory.getCurrentSession().update(Category);
			return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}

	@Override
	public List<category> listcategories() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from category");
		List<category> listCategories=query.list();
		session.close();
		return listCategories;
	}

	@Override
	public category getcategory(int CategoryID) 
	{
		Session session=sessionFactory.openSession();
		category category=session.get(category.class,CategoryID);
		session.close();	
		return category ;
	}


}
