package com.example.eyd;

import android.content.Intent;
import android.os.Bundle;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Data.DataManager;
import com.example.eyd.Model.Material;
import com.example.eyd.Data.SharedPreferenceHelper;
import com.example.eyd.Model.response.MaterialResponse;
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

import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Home extends AppCompatActivity {
    private final DataManager mDataManager = new DataManager();
    private SharedPreferenceHelper sharedPreferenceHelper;
    private Disposable mDisposable;
    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView rvMateri;
    private ImageView Navbar;
    private List<Material> list = new ArrayList<Material>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvMateri = findViewById(R.id.rv_materi);
        rvMateri.setHasFixedSize(true);
        sharedPreferenceHelper = new SharedPreferenceHelper(getApplicationContext());
        //list.addAll(DataMateri.getListData());
        showRecyclerList();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
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

    @Override
    protected void onDestroy() {
        if(mDisposable != null && !mDisposable.isDisposed()) mDisposable.dispose();
        super.onDestroy();
    }

    private void showRecyclerList(){
        Log.i("Home Activity", "Show Recycler List");
        mDisposable = mDataManager.getMaterial("Bearer "+sharedPreferenceHelper.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<List<MaterialResponse>>() {
                            @Override
                            public void accept(List<MaterialResponse> materialResponses) throws Exception {
                                for(int i = 0;i < materialResponses.size();i++){
                                    Material material = new Material(materialResponses.get(i).getMat_id(),
                                            materialResponses.get(i).getTitle(),
                                            materialResponses.get(i).getMaterial(),
                                            materialResponses.get(i).getLink(),
                                            materialResponses.get(i).getCorrection());
                                    list.add(material);
                                }
                                setMaterialAdapter(list);
                                Log.i("Home Activity : ", "DATA RECEIVED");
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("Home Activity : ", throwable.getMessage().toString());
                            }
                        }
                );
    }

    private void setMaterialAdapter(List<Material> list){
        rvMateri.setLayoutManager(new LinearLayoutManager(this));
        MateriAdapter materiAdapter = new MateriAdapter(list);
        rvMateri.setAdapter(materiAdapter);

        materiAdapter.setOnItemClickCallback(new MateriAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Material modelMateri) {
                showSelectedMateri(modelMateri);
            }
        });
    }

    private void showSelectedMateri(Material modelMateri){
        Intent intent = new Intent(Home.this, Materi.class);
        intent.putExtra("material", modelMateri);
        startActivity(intent);
    }

}
