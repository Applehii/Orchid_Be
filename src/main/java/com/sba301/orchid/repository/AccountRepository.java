package com.sba301.orchid.repository;

import com.sba301.orchid.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);
}
