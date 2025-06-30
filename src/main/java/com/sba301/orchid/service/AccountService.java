package com.sba301.orchid.service;

import com.nimbusds.jose.JOSEException;
import com.sba301.orchid.dto.SigninRequest;
import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.pojo.Order;

import java.text.ParseException;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    Account updateAccount(Account account);

    Account getAccountById(String id);

    void deleteAccount(String id);

    List<Account> getAllAccounts();

    String signin(SigninRequest request);

    String refreshToken(String tokenRequest) throws ParseException, JOSEException;
}