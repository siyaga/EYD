package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Data.DataMateri;
import com.example.eyd.Model.ModelMateri;

import java.util.ArrayList;

public class Materi extends AppCompatActivity {
    private static final String VIDEO_URL = "https://cdn.medcom.id/videos/2017/09/11/757085/6W3UigJbDM.mp4";

    ProgressDialog pDialog;
    VideoView videoView;
    MediaController mediaController;
    Uri video;

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
        videoView = (VideoView) findViewById(R.id.VV_materi);

        videoStream();

    }
    private void videoStream() {
        // membuat progressbar
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Buffering ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try {
            // Memulai MediaController
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            // Video URL
            video = Uri.parse(VIDEO_URL);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                // Menutup pDialog dan play video
                public void onPrepared(MediaPlayer mp) {
                    pDialog.dismiss();
                    videoView.start();
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }
}
