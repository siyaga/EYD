package com.example.eyd.model.request;

public class RegisterBody {
    final String fullname;
    final String email;
    final String education;
    final String username;
    final String passwordHash;

    public RegisterBody(String fullname, String email, String education, String username, String passwordHash) {
        this.fullname = fullname;
        this.email = email;
        this.education = education;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
