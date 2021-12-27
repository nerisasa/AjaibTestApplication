package com.nerisa.ajaibtestapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUserRepositorySearchData {

    @SerializedName("avatarUrl")
    private String avatarUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("watchers")
    private String watchers;

    @SerializedName("updated_at")
    private String updated_at;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected GithubUserRepositorySearchData(Parcel in) {
        this.avatarUrl = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.watchers = in.readString();
        this.updated_at = in.readString();
    }
}