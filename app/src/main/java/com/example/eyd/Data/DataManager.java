package com.example.eyd.Data;

import com.example.eyd.Model.request.LoginBody;
import com.example.eyd.Model.request.RegisterBody;
import com.example.eyd.Model.request.ResultBody;
import com.example.eyd.Model.response.LoginResponse;
import com.example.eyd.Model.response.MaterialResponse;
import com.example.eyd.Model.response.QuestionResponse;
import com.example.eyd.Model.response.RegisterResponse;
import com.example.eyd.service.EydService;
import com.example.eyd.service.factory.EydServiceFactory;

import java.util.List;

import io.reactivex.Observable;

public class DataManager {
    private final EydService eydService = EydServiceFactory.getInstance();

    public Observable<LoginResponse> login(LoginBody loginBody){
        return eydService.login(loginBody);
    }

    public Observable<RegisterResponse> register(RegisterBody registerBody){
        return eydService.register(registerBody);
    }

    public Observable<List<QuestionResponse>> getQuestions(String tokenBearer,
                                                           Integer userId,
                                                           Integer matId,
                                                           Integer totalQuestions){
        return eydService.getQuestions(tokenBearer, userId, matId, totalQuestions);
    }

    public Observable<List<MaterialResponse>> getMaterial(String tokenBearer){
        return eydService.getMaterials(tokenBearer);
    }

    public Observable<String> saveResult(List<ResultBody> listResult){
        return eydService.saveResult(listResult);
    }
}
