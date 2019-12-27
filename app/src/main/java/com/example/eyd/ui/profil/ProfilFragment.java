package com.example.eyd.ui.profil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.eyd.Data.SharedPreferenceHelper;
import com.example.eyd.EditProfil;
import com.example.eyd.R;

public class ProfilFragment extends Fragment {
    private Button btn_edit;
    private ProfilViewModel profilViewModel;
    private TextView tvNamaLengkap, tvUsername, tvEmail, tvPendidikan;
    private SharedPreferenceHelper sharedPreferenceHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profilViewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        btn_edit = view.findViewById(R.id.btn_editprofil);
        tvNamaLengkap = view.findViewById(R.id.tv_nama_lengkap);
        tvUsername = view.findViewById(R.id.tv_nama);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPendidikan = view.findViewById(R.id.tv_pendidikan);

        sharedPreferenceHelper = new SharedPreferenceHelper(getActivity().getApplicationContext());

        //set profile
        tvNamaLengkap.setText(sharedPreferenceHelper.getName());
        tvUsername.setText(sharedPreferenceHelper.getUsername());
        tvEmail.setText(sharedPreferenceHelper.getEmail());
        tvPendidikan.setText(sharedPreferenceHelper.getEducation());

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