package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class EditProfil extends AppCompatActivity {
    ImageView Kembali;
    EditText edt_Nama_lengkap, edt_Nama_pengguna, edt_Email;
    Spinner spinner_Pendidikan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);
        Kembali = findViewById(R.id.iv_kembali);
        edt_Nama_lengkap = findViewById(R.id.edt_profil_nama_lengkap);
        edt_Nama_pengguna = findViewById(R.id.edt_profil_nama_pengguna);
        edt_Email = findViewById(R.id.edt_profil_email);
        spinner_Pendidikan = findViewById(R.id.spinner_pendidikan);

        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

}
