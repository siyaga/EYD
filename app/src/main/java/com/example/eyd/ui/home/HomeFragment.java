package com.example.eyd.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Data.DataMateri;
import com.example.eyd.Home;
import com.example.eyd.Materi;
import com.example.eyd.Model.ModelMateri;
import com.example.eyd.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView rvMateri;
    private HomeViewModel homeViewModel;
    private ArrayList<ModelMateri> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvMateri = view.findViewById(R.id.rv_materi);
        rvMateri.setHasFixedSize(true);
        list.addAll(DataMateri.getListData());
        showRecyclerList();

        return view;
    }
    private void showRecyclerList(){
        rvMateri.setLayoutManager(new LinearLayoutManager(getContext()));
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
        Intent intent = new Intent(getContext(), Materi.class);
        startActivity(intent);


    }
}