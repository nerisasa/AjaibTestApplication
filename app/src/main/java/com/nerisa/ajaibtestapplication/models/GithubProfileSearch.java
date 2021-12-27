package com.nerisa.ajaibtestapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubProfileSearch {

    @SerializedName("items")
    private List<GithubProfileSearchData> githubProfileSearchData;

    public List<GithubProfileSearchData> getModelSearchData() {
        return githubProfileSearchData;
    }

    public void setGithubProfileSearchData(List<GithubProfileSearchData> githubProfileSearchData) {
        this.githubProfileSearchData = githubProfileSearchData;
    }

}