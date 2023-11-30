package com.api.users.domain.services;

import com.api.users.domain.entities.User;
import com.api.users.domain.valueobjects.Token;
import reactor.core.publisher.Mono;

public interface TokenService {
    Mono<User> addToken(User user);

    Mono<Token> getTokenByUser(User user);
}
