package com.example.eyd.model.request;

public class LoginBody {
    final String username;
    final String passwordHash;

    public LoginBody(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
