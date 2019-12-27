package com.example.eyd.Model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("passwordHash")
    @Expose
    private String passwordHash;
}
