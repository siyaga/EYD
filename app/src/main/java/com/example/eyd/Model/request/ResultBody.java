package com.example.eyd.Model.request;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultBody implements Parcelable {
    private Integer userId;
    private Boolean result;
    private Integer questionId;

    //parcelable section

    public static final Parcelable.Creator<ResultBody> CREATOR = new Parcelable.Creator<ResultBody>(){
        public ResultBody createFromParcel(Parcel in){
            return new ResultBody(in);
        }

        @Override
        public ResultBody[] newArray(int i) {
            return new ResultBody[i];
        }
    };

    public ResultBody(Parcel in){
        userId = in.readInt();
        if(in.readInt() == -1){
            result = null;
        }else{
            result = (in.readInt() == 1) ? true : false;
        }
        questionId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        if(result == null){
            parcel.writeInt(-1);
        }else{
            parcel.writeInt((int) (result ? 1 : 0));
        }
        parcel.writeInt(questionId);
    }


    //end of parcelable

    public ResultBody(Integer userId, Boolean result, Integer questionId) {
        this.userId = userId;
        this.result = result;
        this.questionId = questionId;
    }
}
