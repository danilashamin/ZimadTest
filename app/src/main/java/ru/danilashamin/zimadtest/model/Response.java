package ru.danilashamin.zimadtest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

    @SerializedName("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }


    public static class Data implements Serializable {
        @SerializedName("title")
        private String title;

        @SerializedName("url")
        private String photoUrl;

        public String getTitle() {
            return title;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }
    }
}
