package com.example.example.Api.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Image {
    @SerializedName("meta")
    public ImageMeta meta;
    @SerializedName("documents")
    public List<ImageDocument> documents;
}

