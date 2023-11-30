package com.api.users.application;

import com.api.users.application.request.CreateUserRequest;
import com.api.users.application.response.UserCreatedResponse;
import com.api.users.domain.entities.Phone;
import com.api.users.domain.entities.User;
import com.api.users.domain.valueobjects.*;
import com.api.users.infrastructure.records.TokenRecord;
import com.api.users.infrastructure.records.UserRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper {
    public static User mapFrom(CreateUserRequest createUserRequest) {
        List<Phone> phones = createUserRequest
                .getPhones()
                .stream()
                .map(phoneRequest -> new Phone(
                                new PhoneNumber(phoneRequest.getNumber()),
                                new CityCode(phoneRequest.getCityCode()),
                                new CountryCode(phoneRequest.getCountryCode())
                        )
                )
                .collect(Collectors.toList());

        return new User(
            new UserId(UUID.randomUUID().toString()),
            new Name(createUserRequest.getName()),
            new Email(createUserRequest.getEmail()),
            new Password(createUserRequest.getPassword()),
            new Active(Boolean.FALSE),
            phones,
            null,
            new CreatedAt(LocalDateTime.now().toString()),
            new ModifiedAt(LocalDateTime.now().toString()),
            new LastLogin(LocalDateTime.now().toString())
        );
    }

    public static User mapTo(TokenRecord tokenRecord, User user) {
        return new User(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getIsActive(),
                user.getPhones(),
                new Token(tokenRecord.getToken()),
                user.getCreatedAt(),
                user.getModifiedAt(),
                user.getLastLogin()
        );
    }

    public static User mapTo(UserRecord userRecord, User user, Token token) {
        return new User(
                new UserId(userRecord.getId().toString()),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getIsActive(),
                user.getPhones(),
                token,
                user.getCreatedAt(),
                user.getModifiedAt(),
                user.getLastLogin()
        );
    }

    public static UserCreatedResponse mapTo(User user) {
        return new UserCreatedResponse(
                user.getUserId().value(),
                user.getCreatedAt().value(),
                user.getModifiedAt().value(),
                user.getLastLogin().value(),
                user.getToken().value(),
                user.getIsActive().value()
        );
    }
}
