package com.sba301.orchid.service;

import com.sba301.orchid.dto.CartItem;
import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.pojo.Orchid;
import com.sba301.orchid.pojo.Order;
import com.sba301.orchid.pojo.OrderDetail;
import com.sba301.orchid.repository.AccountRepository;
import com.sba301.orchid.repository.OrchidRepository;
import com.sba301.orchid.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.PageImpl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrchidServiceImpl implements OrchidService {
    private final OrchidRepository orchidRepository;
    private final AccountRepository accountRepository;
    private final MongoTemplate mongoTemplate;
    private final OrderRepository orderRepository;


    @Override
    public String checkout(String accountId, List<CartItem> cartItems) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double totalAmount = 0;
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (CartItem item : cartItems) {
            Orchid orchid = orchidRepository.findById(item.getOrchidId())
                    .orElseThrow(() -> new RuntimeException("Orchid not found"));

            OrderDetail detail = OrderDetail.builder()
                    .orchid(orchid)  // hoặc lưu toàn bộ thông tin hoa nếu cần
                    .quantity(item.getQuantity())
                    .price(orchid.getPrice())
                    .build();

            totalAmount += orchid.getPrice() * item.getQuantity();
            orderDetails.add(detail);
        }

        Order order = Order.builder()
                .account(account)
                .orderDate(LocalDate.now())
                .orderStatus("pending")
                .totalAmount(totalAmount)
                .orderDetails(orderDetails)
                .build();

        orderRepository.save(order);

        return "Checkout successful";
    }


    @Override
    public Orchid createOrchid(Orchid orchid) {
        return orchidRepository.save(orchid);
    }

    @Override
    public Orchid updateOrchid(Orchid orchid) {
        return orchidRepository.save(orchid);
    }

    @Override
    @Transactional
    public void deleteOrchid(String id) {
        orchidRepository.deleteById(id);

    }

    @Override
    public Orchid getOrchidById(String id) {
        return orchidRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orchid with ID " + id + " does not exist."));
    }

    @Override
    public List<Orchid> getAllOrchids() {
        return orchidRepository.findAll();
    }

    @Override
    public Page<Orchid> getOrchids(String name, String categoryId, Boolean isNatural, Integer from, Integer to, int page, int size) {

        Query query = new Query();

        // Tìm theo tên
        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("orchidName").regex(name, "i"));  // Không phân biệt hoa thường
        }

        // Tìm theo categoryId
        if (categoryId != null && !categoryId.isEmpty()) {
            query.addCriteria(Criteria.where("category.categoryId").is(categoryId));
        }

        // Tìm theo isNatural
        if (isNatural != null) {
            query.addCriteria(Criteria.where("isNatural").is(isNatural));
        }

        // Tìm theo khoảng giá
        if (from != null) {
            query.addCriteria(Criteria.where("price").gte(from));
        }
        if (to != null) {
            query.addCriteria(Criteria.where("price").lte(to));
        }

        // Tổng số bản ghi
        long total = mongoTemplate.count(query, Orchid.class);

        // Phân trang
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "orchidId"));
        query.with(pageable);

        // Truy vấn lấy danh sách
        List<Orchid> orchids = mongoTemplate.find(query, Orchid.class);

        return new PageImpl<>(orchids, pageable, total);
    }

}
