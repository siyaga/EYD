package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eyd.Adapter.MateriAdapter;
import com.example.eyd.Adapter.SoalAdapter;
import com.example.eyd.Data.DataManager;
import com.example.eyd.Data.DataMateri;
import com.example.eyd.Data.DataSoal;
import com.example.eyd.Data.SharedPreferenceHelper;
import com.example.eyd.Model.ModelMateri;
import com.example.eyd.Model.ModelSoal;
import com.example.eyd.Model.request.ResultBody;
import com.example.eyd.Model.response.SaveResultResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Soal extends AppCompatActivity {
    RelativeLayout rlQuestion;
    TextView tvSoalUjian, tvJumlahSoal;
    ImageView Kembali;
    Button Jawab1,Jawab2, lanjut;
    private SharedPreferenceHelper mSharedPreferenceHelper;
    private DataManager mDataManager = new DataManager();
    private Disposable mDisposable;
    private ArrayList<ModelSoal> listSoal;
    private ArrayList<ResultBody> listResult = new ArrayList<ResultBody>();
    private Bundle listSoalBundle;
    private int questionNumber;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        mSharedPreferenceHelper = new SharedPreferenceHelper(this.getApplicationContext());
        listSoalBundle = getIntent().getExtras();
        listSoal = listSoalBundle.getParcelableArrayList("listSoal");
        questionNumber = listSoal.size();
        rlQuestion = findViewById(R.id.rl_question);
        tvSoalUjian = findViewById(R.id.tv_soal_ujian);
        tvJumlahSoal = findViewById(R.id.tv_jumlah_soal);
        Kembali = findViewById(R.id.iv_keluar);
        Jawab1 = findViewById(R.id.btn_jawaban_1);
        Jawab2 = findViewById(R.id.btn_jawaban_2);
        lanjut = findViewById(R.id.btn_soal_lanjut);

        //set total question
        tvJumlahSoal.setText(counter+1 + "/" + questionNumber);

        //initiate first question
        callQuestion(counter);

        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Jawab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer(listSoal.get(counter).getAnswer(), true);
            }
        });
        Jawab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer(listSoal.get(counter).getAnswer(), false);
            }
        });
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter+1 == listSoal.size()){
                    sendResult(listSoal, listResult);
                }else{
                    counter++;
                    callQuestion(counter);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        counter = 0;
        listSoal = null;
    }

    private void callQuestion(int position){
        tvSoalUjian.setText(listSoal.get(position).getSoal());
        Jawab1.setText(listSoal.get(position).getJawaban1());
        Jawab2.setText(listSoal.get(position).getJawaban2());
    }

    private void showAnswer(boolean answer, boolean optionClicked){
        if(answer){
            Jawab1.setBackgroundColor(Color.GREEN);
            Jawab2.setBackgroundColor(Color.RED);
            lanjut.setVisibility(View.VISIBLE);
        }else{
            Jawab1.setBackgroundColor(Color.RED);
            Jawab2.setBackgroundColor(Color.GREEN);
            lanjut.setVisibility(View.VISIBLE);
        }

        Jawab1.setEnabled(false);
        Jawab2.setEnabled(false);

        if(answer == optionClicked) saveAnswer(true); else saveAnswer(false);
    }

    private void saveAnswer(boolean answer){
        ResultBody resultBody = new ResultBody(mSharedPreferenceHelper.getUserId(), answer, listSoal.get(counter).getQuestionId());
        listResult.add(resultBody);
    }

    private void sendResult(final ArrayList<ModelSoal> listSoal, final ArrayList<ResultBody> listResult){
        mDisposable = mDataManager.saveResult("Bearer "+mSharedPreferenceHelper.getToken(), listResult)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<SaveResultResponse>() {
                                @Override
                                public void accept(SaveResultResponse s) throws Exception {
                                    if (s.getStatus().equals("Success")) {
                                        Intent intent = new Intent(Soal.this, HasilLatihanSoal.class);
                                        Bundle listSoalBundleSoal = new Bundle();
                                        listSoalBundleSoal.putParcelableArrayList("listSoal", listSoal);
                                        intent.putExtras(listSoalBundleSoal);
                                        Bundle listResultBundleSoal = new Bundle();
                                        listResultBundleSoal.putParcelableArrayList("listResult", listResult);
                                        intent.putExtras(listResultBundleSoal);
                                        startActivity(intent);
                                    }
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e("Soal Activity : ", throwable.toString());
                                }
                            }
                    );
    }
}
