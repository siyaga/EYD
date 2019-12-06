package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Soal extends AppCompatActivity {
    ImageView Kembali;
    Button Jawab1,Jawab2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        Kembali = findViewById(R.id.iv_kembali);
        Jawab1 = findViewById(R.id.btn_jawaban_1);
        Jawab2 = findViewById(R.id.btn_jawaban_2);

        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Jawab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soal.this, HasilLatihanSoal.class);
                startActivity(intent);
            }
        });
        Jawab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soal.this, HasilLatihanSoal.class);
                startActivity(intent);
            }
        });

    }
}
