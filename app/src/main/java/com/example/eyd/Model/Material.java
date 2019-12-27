package com.example.eyd.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Material implements Parcelable {
    private Integer matId;
    private String title;
    private String material;
    private String link;
    private String correction;

    //parcelable methods
    public static final Parcelable.Creator<Material> CREATOR = new Parcelable.Creator<Material>(){
        public Material createFromParcel(Parcel in){
            return new Material(in);
        }

        public Material[] newArray(int size){
            return new Material[size];
        }
    };

    public Material(Parcel in){
        matId = in.readInt();
        title = in.readString();
        material = in.readString();
        link = in.readString();
        correction = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(matId);
        parcel.writeString(title);
        parcel.writeString(material);
        parcel.writeString(link);
        parcel.writeString(correction);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //end of parcelable

    public Material(Integer matId, String title, String material, String link, String correction){
        this.matId = matId;
        this.title = title;
        this.material = material;
        this.link = link;
        this.correction = correction;
    }

    public Integer getMatId() {
        return matId;
    }

    public void setMatId(Integer matId) {
        this.matId = matId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }
}
