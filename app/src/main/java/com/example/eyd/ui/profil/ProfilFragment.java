package com.example.eyd.ui.profil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.eyd.EditProfil;
import com.example.eyd.R;

public class ProfilFragment extends Fragment {
    private Button btn_edit;
    private ProfilViewModel profilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profilViewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        btn_edit = view.findViewById(R.id.btn_editprofil);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfil.class);
                startActivity(intent);
            }
        });

        return view;
    }
}