package com.api.users.application.controllers;

import com.api.users.application.UserMapper;
import com.api.users.application.Validator;
import com.api.users.application.exceptions.InvalidEmailException;
import com.api.users.application.exceptions.InvalidPasswordException;
import com.api.users.application.request.CreateUserRequest;
import com.api.users.application.response.UserCreatedResponse;
import com.api.users.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(UserController.USER_PATH)
public class UserController {

    @Value("${email.pattern}")
    private String emailPattern;

    @Value("${password.pattern}")
    private String passwordPattern;

    public static final String BASE_PATH = "/api";

    public static final String USER_PATH = BASE_PATH + "/user";

    @Autowired
    private UserService userService;

    @PostMapping
    public Mono<UserCreatedResponse> register(@RequestBody Mono<CreateUserRequest> request) {
        return request.flatMap(createUserRequest -> {
            if (!Validator.isValidEmail(createUserRequest.getEmail(), emailPattern)) {
                return Mono.error(new InvalidEmailException());
            }
            if (!Validator.isValidPassword(createUserRequest.getPassword(), passwordPattern)) {
                return Mono.error(new InvalidPasswordException());
            }
            return Mono.just(UserMapper.mapFrom(createUserRequest));
        })
        .flatMap(user -> userService.createUser(user))
        .map(userCreated -> UserMapper.mapTo(userCreated));
    }
}
