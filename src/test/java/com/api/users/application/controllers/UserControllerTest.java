package com.api.users.application.controllers;

import com.api.users.application.request.CreateUserRequest;
import com.api.users.application.request.PhoneRequest;
import com.api.users.domain.entities.Phone;
import com.api.users.domain.entities.User;
import com.api.users.domain.services.DomainUserService;
import com.api.users.domain.services.UserService;
import com.api.users.domain.valueobjects.*;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void testRegisterUser() {
        User user = buildUser();

        given(userService.createUser(any(User.class)))
                .willReturn(Mono.just(user));

        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(buildCreateUserRequest()), CreateUserRequest.class)
                .exchange();

        responseSpec.expectStatus().isCreated();
    }

    private CreateUserRequest buildCreateUserRequest() {
        return new CreateUserRequest(
                "John Doe",
                "johndoe@testdomain.com",
                "1234",
                Collections.singletonList(
                        new PhoneRequest(
                                "3324567",
                                "4",
                                "1"
                        )
                )
        );
    }

    private User buildUser() {
        return new User(
                new UserId(UUID.randomUUID().toString()),
                new Name("John Doe"),
                new Email("johndoe@testdomain.com"),
                new Password("1234"),
                new Active(Boolean.FALSE),
                Collections.singletonList(
                        new Phone(
                                new PhoneNumber("3324567"),
                                new CityCode("4"),
                                new CountryCode("1")
                        )
                ),
                null,
                new CreatedAt(LocalDateTime.now().toString()),
                new ModifiedAt(LocalDateTime.now().toString()),
                new LastLogin(LocalDateTime.now().toString())
        );
    }
}
