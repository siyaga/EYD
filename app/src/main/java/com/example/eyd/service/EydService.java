package com.example.eyd.service;

import com.example.eyd.model.request.LoginBody;
import com.example.eyd.model.request.RegisterBody;
import com.example.eyd.model.response.LoginResponse;
import com.example.eyd.model.response.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EydService {
    @POST("login/getToken")
    Observable<LoginResponse> login(@Body LoginBody loginBody);

    @POST("/register")
    Observable<RegisterResponse> register(@Body RegisterBody registerBody);
}
