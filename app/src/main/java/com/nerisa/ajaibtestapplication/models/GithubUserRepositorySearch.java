package com.nerisa.ajaibtestapplication.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUserRepositorySearch {

    private List<GithubUserRepositorySearchData> githubUserRepositorySearchData;

    public List<GithubUserRepositorySearchData> getModelUserRepositoryData() {
        return githubUserRepositorySearchData;
    }

    public void setGithubProfileSearchData(List<GithubUserRepositorySearchData> githubUserRepositorySearchData) {
        this.githubUserRepositorySearchData = githubUserRepositorySearchData;
    }

}