package com.api.users.application.controllers;

import com.api.users.application.request.CreateUserRequest;
import com.api.users.application.request.PhoneRequest;
import com.api.users.domain.entities.Phone;
import com.api.users.domain.entities.User;
import com.api.users.domain.valueobjects.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testRegisterUser() {
        webTestClient.post()
                .uri("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(buildCreateUserRequest()))
                .exchange()
                .expectStatus().isOk();
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
