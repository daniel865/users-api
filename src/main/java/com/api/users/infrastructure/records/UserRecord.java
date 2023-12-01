package com.api.users.infrastructure.records;

import com.api.users.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class UserRecord {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLogin;

    public static UserRecord from(User user) {
        return new UserRecord(
            UUID.randomUUID(),
            user.getName().value(),
            user.getEmail().value(),
            user.getPassword().value(),
            user.getIsActive().value(),
            LocalDateTime.parse(user.getCreatedAt().value()),
            LocalDateTime.parse(user.getModifiedAt().value()),
            LocalDateTime.parse(user.getLastLogin().value())
        );
    }
}
