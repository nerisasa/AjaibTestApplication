package com.nerisa.ajaibtestapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GithubProfileSearchData {

    @SerializedName("login")
    private String login;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    protected GithubProfileSearchData(Parcel in) {
        this.login = in.readString();
    }
}