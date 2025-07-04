package com.sba301.orchid.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.sba301.orchid.dto.SigninRequest;
import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.repository.AccountRepository;
import com.sba301.orchid.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final JwtEncoder jwtEncoder;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Value("${jwt.secret}")
    private String signerKey;

    @Override
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if (account.getRole() == null) {
            account.setRole(roleRepository.findByRoleName("USER"));
        }
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + id + " does not exist."));
    }

    @Override
    public void deleteAccount(String id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public String signin(SigninRequest request) {
        Account account = accountRepository.findByEmail(request.email());

        if (account == null || !passwordEncoder.matches(request.password(), account.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return generateToken(account);
    }

    public String refreshToken(String tokenRequest) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(tokenRequest);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expiryTime.before(new Date())) {
            throw new BadCredentialsException("Token is expired");
        }

        String subject = signedJWT.getJWTClaimsSet().getSubject(); // subject lưu accountId
        Account account = getAccountById(subject);

        return generateToken(account);
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        if (!signedJWT.verify(verifier)) {
            throw new BadCredentialsException("Invalid token signature");
        }
        return signedJWT;
    }

    private String generateToken(Account account) {
        return jwtEncoder.encode(
                JwtEncoderParameters.from(
                        JwsHeader.with(() -> JwsAlgorithms.HS512)
                                .type("JWT")
                                .build(),
                        JwtClaimsSet.builder()
                                .subject(account.getAccountId().toString())
                                .claim("scope", account.getRole().getRoleName())
                                .issuedAt(Instant.now())
                                .expiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                                .build()
                )
        ).getTokenValue();
    }
}
