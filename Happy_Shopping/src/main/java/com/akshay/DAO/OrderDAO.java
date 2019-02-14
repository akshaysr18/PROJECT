package com.akshay.DAO;

import com.akshay.model.OrderDetail;

public interface OrderDAO 
{
	public boolean saveOrder(OrderDetail orderDetail);
    public boolean updateCart(String username);
}
