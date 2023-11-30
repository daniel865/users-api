package com.api.users.domain.services;

import com.api.users.domain.entities.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> createUser(User user);
}
