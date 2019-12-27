package com.example.eyd.service;

import com.example.eyd.Model.request.LoginBody;
import com.example.eyd.Model.request.RegisterBody;
import com.example.eyd.Model.request.ResultBody;
import com.example.eyd.Model.response.LoginResponse;
import com.example.eyd.Model.response.MaterialResponse;
import com.example.eyd.Model.response.QuestionResponse;
import com.example.eyd.Model.response.RegisterResponse;
import com.example.eyd.Model.response.SaveResultResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EydService {
    @POST("login/getToken")
    Observable<LoginResponse> login(@Body LoginBody loginBody);

    @POST("/register")
    Observable<RegisterResponse> register(@Body RegisterBody registerBody);

    @FormUrlEncoded
    @POST("/authenticated/fetchQuestions")
    Observable<List<QuestionResponse>> getQuestions(@Header("Authorization") String tokenBearer,
                                                    @Field("userId") Integer userId,
                                                    @Field("matId") Integer matId,
                                                    @Field("totalQuestions") Integer totalQuestions);

    @GET("/authenticated/getMaterials")
    Observable<List<MaterialResponse>> getMaterials(@Header("Authorization") String tokenBearer);

    @POST("/authenticated/saveResult")
    Observable<SaveResultResponse> saveResult(@Header("Authorization") String tokenBearer, @Body List<ResultBody> listResult);
}
