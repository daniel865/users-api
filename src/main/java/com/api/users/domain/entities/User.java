package com.api.users.domain.entities;

import com.api.users.domain.valueobjects.*;

import java.util.List;

public class User {
    private UserId userId;
    private Name name;
    private Email email;
    private Password password;
    private Active isActive;
    private List<Phone> phones;
    private Token token;
    private CreatedAt createdAt;
    private ModifiedAt modifiedAt;
    private LastLogin lastLogin;

    public User(UserId userId, Name name, Email email, Password password, Active isActive, List<Phone> phones, Token token, CreatedAt createdAt, ModifiedAt modifiedAt, LastLogin lastLogin) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.phones = phones;
        this.token = token;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.lastLogin = lastLogin;
    }

    public UserId getUserId() {
        return userId;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Active getIsActive() {
        return isActive;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public Token getToken() {
        return token;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public ModifiedAt getModifiedAt() {
        return modifiedAt;
    }

    public LastLogin getLastLogin() {
        return lastLogin;
    }
}
