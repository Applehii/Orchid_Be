package com.sba301.orchid.repository;

import com.sba301.orchid.pojo.Orchid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrchidRepository extends MongoRepository<Orchid, String> {

    Page<Orchid> findAll(Pageable pageable);  // Hỗ trợ phân trang chuẩn MongoDB

    // Thêm query method tuỳ biến nếu cần, ví dụ:
    Page<Orchid> findByOrchidNameContainsIgnoreCase(String keyword, Pageable pageable);

}
