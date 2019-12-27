package com.example.eyd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.eyd.Data.DataManager;
import com.example.eyd.Data.SharedPreferenceHelper;
import com.example.eyd.Model.ModelSoal;
import com.example.eyd.Model.response.QuestionResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class KomfirmasiSoal extends AppCompatActivity implements View.OnClickListener{
    private final DataManager mDataManager = new DataManager();
    private SharedPreferenceHelper mSharedPrefHelper;
    private Disposable mDisposable;
    private ArrayList<ModelSoal> listSoal = new ArrayList<ModelSoal>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komfirmasi_soal);
        intent = getIntent();
        mSharedPrefHelper = new SharedPreferenceHelper(getApplicationContext());
        ImageView Kembali = findViewById(R.id.iv_kembali);
        Button  btn_soal_10 = findViewById(R.id.btn_10);
        btn_soal_10.setOnClickListener(this);
        Button  btn_soal_25 = findViewById(R.id.btn_25);
        btn_soal_25.setOnClickListener(this);
        Button  btn_soal_50 = findViewById(R.id.btn_50);
        btn_soal_50.setOnClickListener(this);


        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onClick(View v) {
        int questionNumber = 0;
        switch (v.getId()){
            case R.id.btn_10:
                questionNumber = 10;
                break;
            case R.id.btn_25:
                questionNumber = 25;
                break;
            case R.id.btn_50:
                questionNumber = 50;
                break;
        }

        goToQuiz(questionNumber);

    }

    private void goToQuiz(final int questionNumber){
        mDisposable = mDataManager.getQuestions("Bearer "+mSharedPrefHelper.getToken(), mSharedPrefHelper.getUserId(), intent.getIntExtra("matId", -1), questionNumber)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            new Consumer<List<QuestionResponse>>() {
                                @Override
                                public void accept(List<QuestionResponse> questionResponses) throws Exception {
                                    for(int i = 0; i<questionResponses.size();i++){
                                        ModelSoal modelSoal = new ModelSoal(
                                                questionResponses.get(i).getQuestionId(),
                                                questionResponses.get(i).getQuestion(),
                                                questionResponses.get(i).getAnswerA(),
                                                questionResponses.get(i).getAnswerB(),
                                                questionResponses.get(i).getAnswer().equals('a') ? true : false
                                        );

                                        listSoal.add(modelSoal);
                                    }
                                    intent = new Intent(KomfirmasiSoal.this, Soal.class);
                                    Bundle listSoalBundle = new Bundle();
                                    listSoalBundle.putParcelableArrayList("listSoal", listSoal);
                                    intent.putExtras(listSoalBundle);
                                    KomfirmasiSoal.this.startActivity(intent);
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e("Konfirmasi Activity : ", throwable.toString());
                                }
                            }
                    );
    }
}
