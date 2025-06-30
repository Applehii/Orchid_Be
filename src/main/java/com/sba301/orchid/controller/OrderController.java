package com.sba301.orchid.controller;

import com.sba301.orchid.dto.CartItem;
import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.pojo.Order;
import com.sba301.orchid.pojo.OrderDetail;
import com.sba301.orchid.pojo.Orchid;
import com.sba301.orchid.repository.AccountRepository;
import com.sba301.orchid.repository.OrderRepository;
import com.sba301.orchid.repository.OrchidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final OrchidRepository orchidRepository;

    @PostMapping("/checkout/{accountId}")
    public String checkout(@PathVariable Integer accountId, @RequestBody List<CartItem> cartItems) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double totalAmount = 0;

        Order order = new Order();
        order.setAccount(account);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus("pending");

        for (CartItem item : cartItems) {
            Orchid orchid = orchidRepository.findById(item.getOrchidId())
                    .orElseThrow(() -> new RuntimeException("Orchid not found"));

            OrderDetail detail = new OrderDetail();
            detail.setOrchid(orchid);
            detail.setQuantity(item.getQuantity());
            detail.setPrice(orchid.getPrice());

            totalAmount += orchid.getPrice() * item.getQuantity();
            detail.setOrder(order);

            order.getOrderDetails().add(detail); // Thêm vào danh sách
        }

        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        return "Checkout successful";
    }
}
