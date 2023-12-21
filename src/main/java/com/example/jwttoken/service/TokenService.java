package com.example.jwttoken.service;

import com.example.jwttoken.model.Token;
import com.example.jwttoken.model.User;
import com.example.jwttoken.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void save(Token token) {
        tokenRepository.save(token);
    }

    public void getUsersTokenByUser(User user) {
        tokenRepository.findAllValidTokensByUser(user.getId());
    }


}
