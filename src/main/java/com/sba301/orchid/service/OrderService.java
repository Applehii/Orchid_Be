package com.sba301.orchid.service;

import com.sba301.orchid.pojo.Order;
import com.sba301.orchid.pojo.OrderDetail;

import java.util.List;

public interface OrderService {
    Order createOrder(List<OrderDetail> orderDetails);
    Order updateOrder(Order order);
    void deleteOrder(String id);
    Order getOrderById(String id);
    List<Order> getOrdersByAccountId(String accountId);
    List<Order> getAllOrders();
}
