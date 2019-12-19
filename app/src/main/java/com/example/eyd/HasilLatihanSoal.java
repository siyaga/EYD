package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.eyd.Adapter.SoalAdapter;
import com.example.eyd.Data.DataSoal;
import com.example.eyd.Model.ModelSoal;

import java.util.ArrayList;

public class HasilLatihanSoal extends AppCompatActivity {
    ImageView Kembali;
    private RecyclerView rvSoal;
    private ArrayList<ModelSoal> list = new ArrayList<>();
    Button btn_kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_latihan_soal);
        Kembali = findViewById(R.id.iv_kembali);
        btn_kembali = findViewById(R.id.btn_kembali_materi);
        rvSoal = findViewById(R.id.rv_soal);
        rvSoal.setHasFixedSize(true);

        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilLatihanSoal.this, Materi.class);
                startActivity(intent);
            }
        });


        list.addAll(DataSoal.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvSoal.setLayoutManager(new LinearLayoutManager(this));
        SoalAdapter soalAdapter = new SoalAdapter(list);
        rvSoal.setAdapter(soalAdapter);

        soalAdapter.setOnItemClickCallback(new SoalAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(ModelSoal modelSoal) {
                showSelectedMateri(modelSoal);
            }
        });


    }
    private void showSelectedMateri(ModelSoal modelSoal){

    }
}
