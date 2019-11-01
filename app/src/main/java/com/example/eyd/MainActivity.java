package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Data.DataMateri;
import com.example.eyd.Model.ModelMateri;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMateri;
    private ImageView Navbar;
    private ArrayList<ModelMateri> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMateri = findViewById(R.id.rv_materi);
        rvMateri.setHasFixedSize(true);
        Navbar = findViewById(R.id.iv_menu);
        Navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        list.addAll(DataMateri.getListData());
        showRecyclerList();
    }
    private void showRecyclerList(){
        rvMateri.setLayoutManager(new LinearLayoutManager(this));
        MateriAdapter materiAdapter = new MateriAdapter(list);
        rvMateri.setAdapter(materiAdapter);

        materiAdapter.setOnItemClickCallback(new MateriAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(ModelMateri modelMateri) {
                showSelectedMateri(modelMateri);
            }
        });


    }
    private void showSelectedMateri(ModelMateri modelMateri){
        Intent intent = new Intent(MainActivity.this, Materi.class);
        startActivity(intent);


    }

}
