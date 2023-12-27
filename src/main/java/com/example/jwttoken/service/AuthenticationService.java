package com.example.jwttoken.service;

import com.example.jwttoken.request.AuthRequest;
import com.example.jwttoken.model.Token;
import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.TokenRepository;
import com.example.jwttoken.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService, TokenRepository tokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
    }

    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            var user = userService.getByUsername(authRequest.getUsername()).orElseThrow();
            var stringToken = jwtService.generateToken(authRequest.getUsername());
            allTokenExpired(user);
            saveToken(user, stringToken);
            user.setToken(stringToken);
            return LoginResponse.builder().user(user).build();
        }
        log.info("Invalid username {} " + authRequest.getUsername());
        throw new UsernameNotFoundException("invalid username {}" + authRequest.getUsername());
    }

    public void saveToken(User user, String stringToken) {
        Token token = Token.builder()
                .user_token(stringToken)
                .user(user)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public void allTokenExpired(User user) {
        log.info("Expire Tokens username : {}" + user.getUsername());
        var validUsersToken = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUsersToken.isEmpty()) {
            return;
        }
        validUsersToken.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUsersToken);
    }
}
