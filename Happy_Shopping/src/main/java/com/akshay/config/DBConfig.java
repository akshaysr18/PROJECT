package com.akshay.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.akshay.model.category;
import com.akshay.model.CartItem;
import com.akshay.model.OrderDetail;
import com.akshay.model.Product;
import com.akshay.model.Supplier;
import com.akshay.model.UserDetail;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.akshay")
public class DBConfig 
{
	
	//1. We need to create a Data Source
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/Project");
		dataSource.setUsername("akshaysr");
		dataSource.setPassword("akshay");
		System.out.println("---DataSource Object is Created----");
		return dataSource;
	}
	
	//2. We need to Create LocalSessionFactoryBuilder object which is used to get SessionFactory object
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateProp=new Properties();
		
		hibernateProp.put("hibernate.hbm2ddl.auto","update");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
		LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(getH2DataSource());
		factory.addProperties(hibernateProp);
		
		factory.addAnnotatedClass(category.class);
		factory.addAnnotatedClass(Supplier.class);
		factory.addAnnotatedClass(Product.class);
		factory.addAnnotatedClass(UserDetail.class);
		factory.addAnnotatedClass(CartItem.class);
		factory.addAnnotatedClass(OrderDetail.class);
		System.out.println("---SessionFactory Object  Created ----");
		
		return factory.buildSessionFactory();
	}
	
	@Bean(name="txManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("---Transaction Manager object Created--");
		return new HibernateTransactionManager(sessionFactory);
	}
	
}