package com.api.users.domain.services;

import com.api.users.application.UserMapper;
import com.api.users.domain.entities.User;
import com.api.users.domain.valueobjects.Token;
import com.api.users.infrastructure.TokenRepository;
import com.api.users.infrastructure.records.TokenRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class DomainTokenService implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Mono<User> addToken(User user) {
        Mono<TokenRecord> savedToken = tokenRepository.save(TokenRecord.from(user));
        return savedToken.map(tokenRecord -> UserMapper.mapTo(tokenRecord, user));
    }

    @Override
    public Mono<Token> getTokenByUser(User user) {
        Mono<TokenRecord> foundToken = tokenRepository.findByUserId(UUID.fromString(user.getUserId().value()));
        return foundToken.map(tokenRecord -> new Token(tokenRecord.getToken()));
    }
}
