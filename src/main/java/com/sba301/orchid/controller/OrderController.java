package com.sba301.orchid.controller;

import com.sba301.orchid.config.AuthContext;
import com.sba301.orchid.dto.CartItem;
import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.pojo.Order;
import com.sba301.orchid.service.AccountService;
import com.sba301.orchid.service.OrchidService;
import com.sba301.orchid.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final AccountService accountService;
    private final AuthContext authContext;
    private final OrderService orderService;
    private final OrchidService orchidService;

    @PostMapping("/checkout/{accountId}")
    public ResponseEntity<String> checkout(@PathVariable String accountId, @RequestBody List<CartItem> cartItems) {
        return ResponseEntity.ok(orchidService.checkout(accountId, cartItems));
    }

    @GetMapping("/accounts/me/orders")
    public ResponseEntity<List<Order>> getMyOrders() {
        Account account = accountService.getAccountById(authContext.getUserId());
        List<Order> orders = orderService.getOrdersByAccountId(account.getAccountId());
        return ResponseEntity.ok(orders);
    }
}
