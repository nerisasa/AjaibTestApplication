package com.nerisa.ajaibtestapplication.repositories

import android.content.Context
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class ProfileDetailRepository {

    interface ResponseHandler {
        fun onSuccess(response: JSONObject)
        fun onSuccessArray(response: JSONArray)
        fun onFailure(message: String)
    }

    fun requestGetUserRepository(context: Context, username: String, responseHandler: ResponseHandler, error500: String) {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url("https://api.github.com/users/$username/repos")
            .addHeader("Authorization", "Basic bmVyaXNhc2E6Z2hwX3NYR2RnandVS0EwUjZlZzdEaGxJUG5sYTdJWGR1RzJZeG5sSQ==")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                responseHandler.onFailure("Unable to process your request, please check your connection and try again")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                var statusCode: Int = response.code()

                if (statusCode == 400 || statusCode == 401 ) {
                    val responseBody = response.body()!!.string()
                    val jsonBody = JSONObject(responseBody)
                    var error = jsonBody.getString("error")

                    if(error == "Token not provided."){
                        error = "Unauthorized. Please Login to access this menu."
                    }

                    responseHandler.onFailure(error)
                }

                if (statusCode == 500) {
                    if (error500 != "") {
                        responseHandler.onFailure(error500)
                    }
                }

                if (statusCode == 200) {
                    val responseBody = response.body()!!.string()
                    val jsonBody = JSONArray(responseBody)

                    responseHandler.onSuccessArray(jsonBody)
                }
            }
        })
    }
}