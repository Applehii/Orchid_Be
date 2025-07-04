package com.sba301.orchid.service;

import com.sba301.orchid.dto.CartItem;
import com.sba301.orchid.pojo.Orchid;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrchidService {
    String checkout(String accountId, List<CartItem> cartItems);
    Orchid createOrchid(Orchid orchid);
    Orchid updateOrchid(Orchid orchid);
    void deleteOrchid(String id);
    Orchid getOrchidById(String id);
    List<Orchid> getAllOrchids();
    Page<Orchid> getOrchids(String name, String categoryId, Boolean isNatural, Integer from, Integer to, int page, int size);
}
