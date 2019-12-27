package com.example.eyd.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.eyd.Data.DataManager;
import com.example.eyd.Data.SharedPreferenceHelper;
import com.example.eyd.Home;
import com.example.eyd.Materi;
import com.example.eyd.Model.Material;
import com.example.eyd.Model.response.MaterialResponse;
import com.example.eyd.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private final DataManager mDataManager = new DataManager();
    private SharedPreferenceHelper sharedPreferenceHelper;
    private Disposable mDisposable;
    private RecyclerView rvMateri;
    private List<Material> list = new ArrayList<Material>();
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        rvMateri = root.findViewById(R.id.rv_materi);
        sharedPreferenceHelper = new SharedPreferenceHelper(getActivity().getApplicationContext());
        showRecyclerList();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mDisposable != null && !mDisposable.isDisposed()) mDisposable.dispose();
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
        rvMateri.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        Intent intent = new Intent(getActivity(), Materi.class);
        intent.putExtra("material", modelMateri);
        startActivity(intent);
    }
}