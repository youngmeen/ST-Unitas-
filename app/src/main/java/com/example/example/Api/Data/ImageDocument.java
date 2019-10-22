package com.example.example.Api.Data;

import com.google.gson.annotations.SerializedName;

public class ImageDocument {
    /**
     * json 형식의 response 데이터를 받기 위한 멤버 변수
     *
     * 주의) 실제 api 서버에서 돌아오는 json 형식의 response 데이터의 필드명과 동일해야 함
     */
    @SerializedName("collection")
    public String collection;
    @SerializedName("thumbnail_url")
    public String thumbnail_url;
    @SerializedName("Image_url")
    public String Image_url;
    @SerializedName("width")
    public int width;
    @SerializedName("height")
    public int height;
    @SerializedName("display_sitename")
    public String display_sitename;
    @SerializedName("doc_url")
    public String doc_url;
    @SerializedName("datetime")
    public String datetime;
    @SerializedName("favorite")
    boolean favorite = false;

    public String getCollection() {
        return collection;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public String getImage_url() {
        return Image_url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getDisplay_sitename() {
        return display_sitename;
    }

    public String getDoc_url() {
        return doc_url;
    }

    public String getDatetime() {
        return datetime;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
