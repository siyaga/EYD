package com.example.eyd.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelSoal implements Parcelable {
    private Integer questionId;
    private String Soal;
    private String Jawaban1;
    private String Jawaban2;
    //if true, the correct answer is answer 1. if false, the correct answer is answer 2.
    private boolean answer;

    //start of parcelable

    public static final Parcelable.Creator<ModelSoal> CREATOR = new Parcelable.Creator<ModelSoal>(){
        public ModelSoal createFromParcel(Parcel in){
            return new ModelSoal(in);
        }

        public ModelSoal[] newArray(int size){
            return new ModelSoal[size];
        }
    };

    public ModelSoal(Parcel in){
        questionId = in.readInt();
        Soal = in.readString();
        Jawaban1 = in.readString();
        Jawaban2 = in.readString();
        answer = (in.readByte() == 1) ? true : false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(questionId);
        parcel.writeString(Soal);
        parcel.writeString(Jawaban1);
        parcel.writeString(Jawaban2);
        parcel.writeByte((byte) (answer ? 1 : 0));
    }

    //end of parcelable

    public ModelSoal(Integer questionId, String soal, String jawaban1, String jawaban2, boolean answer) {
        this.questionId = questionId;
        Soal = soal;
        Jawaban1 = jawaban1;
        Jawaban2 = jawaban2;
        this.answer = answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getSoal() {
        return Soal;
    }

    public void setSoal(String soal) {
        this.Soal = soal;
    }

    public String getJawaban1() {
        return Jawaban1;
    }

    public void setJawaban1(String jawaban1) {
        this.Jawaban1 = jawaban1;
    }

    public String getJawaban2() {
        return Jawaban2;
    }

    public void setJawaban2(String jawaban2) {
        this.Jawaban2 = jawaban2;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
