package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class KomfirmasiSoal extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komfirmasi_soal);
        ImageView Kembali = findViewById(R.id.iv_kembali);
        Button  btn_soal_10 = findViewById(R.id.btn_10);
        btn_soal_10.setOnClickListener(this);
        Button  btn_soal_25 = findViewById(R.id.btn_25);
        btn_soal_25.setOnClickListener(this);
        Button  btn_soal_50 = findViewById(R.id.btn_50);
        btn_soal_50.setOnClickListener(this);


        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_10:
                Intent int_10 = new Intent(this, Soal.class);
                startActivity(int_10);
                break;
            case R.id.btn_25:
                Intent int_25 = new Intent(this, Soal.class);
                startActivity(int_25);
                break;
            case R.id.btn_50:
                Intent int_50 = new Intent(this, Soal.class);
                startActivity(int_50);
                break;
        }

    }
}
