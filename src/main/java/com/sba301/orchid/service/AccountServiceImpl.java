package com.sba301.orchid.service;

import com.sba301.orchid.dto.SigninRequest;
import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.repository.AccountRepository;
import com.sba301.orchid.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final RoleRepository roleRepository;
    @Override
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(roleRepository.findByRoleName("USER"));
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(String id) {
        return accountRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " does not exist."));
    }

    @Override
    public void deleteAccount(String id) {
        Account account = accountRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " does not exist."));
        accountRepository.delete(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public String signin(SigninRequest request) {
        Account account = accountRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));


        if (!passwordEncoder.matches(request.password(), account.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return jwtEncoder.encode(
                JwtEncoderParameters.from(
                        JwsHeader.with(() -> JwsAlgorithms.HS512)
                                .type("JWT")
                                .build(),
                    JwtClaimsSet.builder()
                            .subject(account.getAccountId().toString())
                            .claim("scope", account.getRole().getRoleName())
                            .issuedAt(Instant.now())
                            .expiresAt(Instant.now().plus(1, ChronoUnit.DAYS)) // 24 hours
                            .build()
                )
        ).getTokenValue();
    }
}
