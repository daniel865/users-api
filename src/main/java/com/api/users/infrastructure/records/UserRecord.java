package com.api.users.infrastructure.records;

import com.api.users.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class UserRecord {
    @Id
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
            convertDate(user.getCreatedAt().value()),
            convertDate(user.getModifiedAt().value()),
            convertDate(user.getLastLogin().value())
        );
    }

    private static LocalDateTime convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }
}
