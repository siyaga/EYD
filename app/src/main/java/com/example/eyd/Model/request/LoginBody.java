package com.example.eyd.Model.request;

public class LoginBody {
    final String username;
    final String passwordHash;

    public LoginBody(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
