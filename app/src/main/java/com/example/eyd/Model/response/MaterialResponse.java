package com.example.eyd.Model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialResponse {
    @SerializedName("mat_id")
    @Expose
    private Integer mat_id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("material")
    @Expose
    private String material;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("correction")
    @Expose
    private String correction;

    public Integer getMat_id() {
        return mat_id;
    }

    public void setMat_id(Integer mat_id) {
        this.mat_id = mat_id;
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
