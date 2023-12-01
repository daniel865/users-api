package com.api.users.infrastructure.records;


import com.api.users.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tokens")
public class TokenRecord {
    private UUID userId;
    private String token;

    public static TokenRecord from(User user) {
        return new TokenRecord(UUID.fromString(user.getUserId().value()), user.getToken().value());
    }
}
