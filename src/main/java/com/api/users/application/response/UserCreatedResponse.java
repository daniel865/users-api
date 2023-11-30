package com.api.users.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedResponse {
    private String id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private Boolean isActive;
}
