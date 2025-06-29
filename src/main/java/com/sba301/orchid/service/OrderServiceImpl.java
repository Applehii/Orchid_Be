package com.sba301.orchid.service;

import com.sba301.orchid.config.AuthContext;
import com.sba301.orchid.pojo.Order;
import com.sba301.orchid.pojo.OrderDetail;
import com.sba301.orchid.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AuthContext authContext;
    private final AccountService accountService;

    @Override
    public Order createOrder(List<OrderDetail> orderDetails) {


        Order order = new Order();
        order.setOrderDetails(orderDetails);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus("PENDING");
        order.setTotalAmount(orderDetails.stream()
                .mapToDouble(OrderDetail::getPrice)
                .sum());
        order.setAccount(accountService.getAccountById(authContext.getUserId()));
        for (OrderDetail detail : orderDetails) {
            detail.setOrder(order);
        }
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        Order order = orderRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + id + " does not exist."));
        orderRepository.delete(order);
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Order with ID " + id + " does not exist."));
    }

    @Override
    public List<Order> getOrdersByAccountId(String accountId) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getAccount().getAccountId().equals(Integer.parseInt(accountId)))
                .toList();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
