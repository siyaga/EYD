package com.example.eyd;

import android.content.Intent;
import android.os.Bundle;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Data.DataMateri;
import com.example.eyd.Model.ModelMateri;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.ImageView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView rvMateri;
    private ImageView Navbar;
    private ArrayList<ModelMateri> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvMateri = findViewById(R.id.rv_materi);
        rvMateri.setHasFixedSize(true);


        list.addAll(DataMateri.getListData());
        showRecyclerList();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
        Intent intent = new Intent(Home.this, Materi.class);
        startActivity(intent);


    }
}
