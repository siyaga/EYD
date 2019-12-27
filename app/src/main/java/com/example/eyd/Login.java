package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eyd.Data.DataManager;
import com.example.eyd.Data.SharedPreferenceHelper;
import com.example.eyd.Model.request.LoginBody;
import com.example.eyd.Model.response.LoginResponse;
import com.example.eyd.util.ShaEncryptor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class Login extends AppCompatActivity {
    Button btnLogin;
    TextView BuatAkun, LupaPassword;
    EditText etUsername, etPassword;
    private final DataManager mDataManager = new DataManager();
    private SharedPreferenceHelper sharedPreferenceHelper;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        BuatAkun = findViewById(R.id.tv_buat_akun);
        LupaPassword = findViewById(R.id.tv_lupa_kata_sandi);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        sharedPreferenceHelper = new SharedPreferenceHelper(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        BuatAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        LupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, LupaKataSandi.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(mDisposable != null && !mDisposable.isDisposed()) mDisposable.dispose();
        super.onDestroy();
    }

    private void login(){
        LoginBody loginBody =
                new LoginBody(
                        etUsername.getText().toString(),
                        ShaEncryptor.encryptSHA1(etPassword.getText().toString())
                );

        mDisposable = mDataManager.login(loginBody)
                .map(new Function<LoginResponse, LoginResponse>() {
                    @Override
                    public LoginResponse apply(LoginResponse loginResponse) throws Exception {
                        sharedPreferenceHelper.setUserId(loginResponse.getUserId());
                        sharedPreferenceHelper.setUsername(loginResponse.getUsername());
                        sharedPreferenceHelper.setName(loginResponse.getName());
                        sharedPreferenceHelper.setEmail(loginResponse.getEmail());
                        sharedPreferenceHelper.setEducation(loginResponse.getEducation());
                        sharedPreferenceHelper.setToken(loginResponse.getToken());

                        return loginResponse;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<LoginResponse>() {
                            @Override
                            public void accept(LoginResponse loginResponse) throws Exception {
                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);
                            }
                        },
                        new Consumer<Throwable>(){
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.i("Login Activity", "Error : " + throwable.toString());
                            }
                        }
                );
    }


}
