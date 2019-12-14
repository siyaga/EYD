package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eyd.data.DataManager;
import com.example.eyd.model.request.LoginBody;
import com.example.eyd.model.response.LoginResponse;
import com.example.eyd.util.ShaEncryptor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class Login extends AppCompatActivity {

    TextView BuatAkun, LupaPassword;
    EditText etUsername, etPassword;
    Button btnLogin;
    private final DataManager mDataManager = new DataManager();
    private LoginResponse loginResponse;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BuatAkun = findViewById(R.id.tv_buat_akun);
        LupaPassword = findViewById(R.id.tv_lupa_kata_sandi);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
        // TODO : password masih dihardcode pake akun yang udah terdaftar
        LoginBody loginBody =
                new LoginBody(
                        etUsername.getText().toString(),
                        //ShaEncryptor.encryptSHA1(etPassword.getText().toString()
                        "n21jskamwo0evmwkdoai2jghu9djen21"
                );

        // TODO : login response belum disave di shared pref
        mDisposable = mDataManager.login(loginBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<LoginResponse>() {
                            @Override
                            public void accept(LoginResponse loginResponse) throws Exception {
                                Intent intent = new Intent(Login.this, MainActivity.class);
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
