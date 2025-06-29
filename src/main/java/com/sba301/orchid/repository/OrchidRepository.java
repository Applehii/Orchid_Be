package com.sba301.orchid.repository;

import com.sba301.orchid.pojo.Orchid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrchidRepository extends JpaRepository<Orchid, Integer>, JpaSpecificationExecutor<Orchid> {
    // Additional query methods can be defined here if needed
}
