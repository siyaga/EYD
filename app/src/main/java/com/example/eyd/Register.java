package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.eyd.Data.DataManager;
import com.example.eyd.Model.request.RegisterBody;
import com.example.eyd.Model.response.RegisterResponse;
import com.example.eyd.util.ShaEncryptor;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Register extends AppCompatActivity {
    private final DataManager mDataManager = new DataManager();
    private Disposable mDisposable;
    ImageView Kembali;
    EditText etFullname, etUsername, etEmail, etPassword;
    Spinner spinEducation;
    Button btnBuatAkun;
    String[] educationArray = {"Sekolah Dasar", "Sekolah Menengah Pertama", "Sekolah Menengah Atas", "Tingkat Lanjut"};
    String[] education = {"sd", "smp", "sma", "tingkat_lanjut"};
    HashMap<Integer, String> spinnerMap = new HashMap<Integer, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Kembali = findViewById(R.id.iv_kembali);
        etFullname = findViewById(R.id.et_fullname);
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        spinEducation = findViewById(R.id.spin_education);
        btnBuatAkun = findViewById(R.id.btn_buat_akun);

        //spinner adapter
        for(int i = 0; i<educationArray.length;i++){
            spinnerMap.put(i, education[i]);
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_eduction, educationArray);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinEducation.setAdapter(spinnerAdapter);

        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnBuatAkun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){
        RegisterBody registerBody =
                new RegisterBody(
                        etFullname.getText().toString(),
                        etEmail.getText().toString(),
                        spinnerMap.get(spinEducation.getSelectedItemPosition()),
                        etUsername.getText().toString(),
                        ShaEncryptor.encryptSHA1(etPassword.getText().toString())
                );

        mDisposable = mDataManager.register(registerBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<RegisterResponse>() {
                            @Override
                            public void accept(RegisterResponse registerResponse) throws Exception {
                                Intent intent = new Intent(Register.this, Home.class);
                                startActivity(intent);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.i("Register Activity", "Error : " + throwable.toString());
                            }
                        }
                );
    }
}
