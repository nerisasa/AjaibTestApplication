package com.nerisa.ajaibtestapplication.models

import com.google.gson.annotations.SerializedName

class GithubProfileDetail {

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("login")
    var login: String? = null

    @SerializedName("occupation")
    var occupation: String? = null

    @SerializedName("location")
    var location: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("followers")
    var followers: String? = null

    @SerializedName("following")
    var following: String? = null
}