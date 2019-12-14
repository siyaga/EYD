package com.example.eyd.data;

import com.example.eyd.model.request.LoginBody;
import com.example.eyd.model.request.RegisterBody;
import com.example.eyd.model.response.LoginResponse;
import com.example.eyd.model.response.RegisterResponse;
import com.example.eyd.service.EydService;
import com.example.eyd.service.factory.EydServiceFactory;

import io.reactivex.Observable;

public class DataManager {
    private final EydService eydService = EydServiceFactory.getInstance();

    public Observable<LoginResponse> login(LoginBody loginBody){
        return eydService.login(loginBody);
    }

    public Observable<RegisterResponse> register(RegisterBody registerBody){
        return eydService.register(registerBody);
    }
}
