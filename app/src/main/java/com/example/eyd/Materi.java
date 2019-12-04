package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Data.DataMateri;
import com.example.eyd.Model.ModelMateri;

import java.util.ArrayList;

public class Materi extends AppCompatActivity {
    ImageView Kembali;
    Button btnSoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        Kembali = findViewById(R.id.iv_kembali);
        btnSoal = findViewById(R.id.btn_soal);

        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Materi.this,KomfirmasiSoal.class);
                startActivity(intent);
            }
        });

    }
}
