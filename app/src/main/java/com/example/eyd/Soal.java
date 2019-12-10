package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Adapter.SoalAdapter;
import com.example.eyd.Data.DataMateri;
import com.example.eyd.Data.DataSoal;
import com.example.eyd.Model.ModelMateri;
import com.example.eyd.Model.ModelSoal;

import java.util.ArrayList;

public class Soal extends AppCompatActivity {
    ImageView Kembali;
    Button Jawab1,Jawab2, lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        Kembali = findViewById(R.id.iv_keluar);
        Jawab1 = findViewById(R.id.btn_jawaban_1);
        Jawab2 = findViewById(R.id.btn_jawaban_2);
        lanjut = findViewById(R.id.btn_soal_lanjut);


        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Jawab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Jawab1.setBackgroundColor(Color.RED);
                Jawab2.setEnabled(false);
                lanjut.setVisibility(View.VISIBLE);
            }
        });
        Jawab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Jawab2.setBackgroundColor(Color.GREEN);
                Jawab1.setEnabled(false);
                lanjut.setVisibility(View.VISIBLE);
            }
        });
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soal.this, HasilLatihanSoal.class);
                startActivity(intent);
            }
        });



    }
}
