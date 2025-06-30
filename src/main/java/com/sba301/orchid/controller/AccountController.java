package com.sba301.orchid.controller;

import com.nimbusds.jose.JOSEException;
import com.sba301.orchid.config.AuthContext;
import com.sba301.orchid.dto.SigninRequest;
import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {
    private final AccountService accountService;
    private final AuthContext authContext;

    @PostMapping("/auth/signin")
    public ResponseEntity<String>  signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(accountService.signin(request));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<Account> signup(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/accounts/me")
    public ResponseEntity<Account> getMyAccount() {
        return ResponseEntity.ok(accountService.getAccountById(authContext.getUserId()));
    }

    @PostMapping("/refresh")
    ResponseEntity<String> authenticate(@RequestBody String request)
            throws ParseException, JOSEException {
        return ResponseEntity.ok( accountService.refreshToken(request));
    }


}
