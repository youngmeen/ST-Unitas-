package com.example.example.Api.Data;

import com.google.gson.annotations.SerializedName;

class ImageMeta {

    @SerializedName("total_count")
    int total_count;
    @SerializedName("pageable_count")
    int pageable_count;
    @SerializedName("is_end")
    boolean is_end;
}
