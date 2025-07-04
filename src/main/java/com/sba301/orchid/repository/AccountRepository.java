package com.sba301.orchid.repository;

import com.sba301.orchid.pojo.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByEmail(String email);

}
