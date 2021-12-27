package com.nerisa.ajaibtestapplication.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nerisa.ajaibtestapplication.cores.Constants
import com.nerisa.ajaibtestapplication.cores.SingleLiveEvent
import com.nerisa.ajaibtestapplication.models.*
import com.nerisa.ajaibtestapplication.rest.APIClient
import com.nerisa.ajaibtestapplication.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    var mutableLiveDataGithubProfileSearchData = MutableLiveData<MutableList<GithubProfileSearchData>>()
    var mutableLiveDataGithubProfileDetail = MutableLiveData<MutableList<GithubProfileDetail>>()

    var newGithubProfileDetailList = mutableListOf<GithubProfileDetail>()

    var toastSingleLiveEvent = SingleLiveEvent<String>()
    init {
        mutableLiveDataGithubProfileSearchData = MutableLiveData()
        mutableLiveDataGithubProfileDetail = MutableLiveData()
        toastSingleLiveEvent = SingleLiveEvent()
    }

    fun callSearchUserAPI(query: String?) {
        val apiService: ApiInterface = APIClient.getClient().create(ApiInterface::class.java)
        val call: Call<GithubProfileSearch> =
            apiService.searchUser(Constants.APIKEY, query, "1", "10")
        call.enqueue(object : Callback<GithubProfileSearch?> {
            override fun onResponse(call: Call<GithubProfileSearch?>, response: Response<GithubProfileSearch?>) {
                if (!response.isSuccessful) {
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items: ArrayList<GithubProfileSearchData> = ArrayList<GithubProfileSearchData>(response.body()!!.modelSearchData)
                    mutableLiveDataGithubProfileSearchData.postValue(items)
                }
            }

            override fun onFailure(call: Call<GithubProfileSearch?>, t: Throwable) {
                Log.e("failure", t.toString())
            }
        })
    }

    fun callSearchUserDetailAPI(userList: MutableList<GithubProfileSearchData>) {
        newGithubProfileDetailList = mutableListOf()

        val apiService: ApiInterface = APIClient.getClient().create(ApiInterface::class.java)
        for ((index, value) in userList.withIndex()) {
            val call: Call<GithubProfileDetail> = apiService.detailUser(Constants.APIKEY, value.login)
            call.enqueue(object : Callback<GithubProfileDetail?> {
                override fun onResponse(call: Call<GithubProfileDetail?>, response: Response<GithubProfileDetail?>) {
                    if (!response.isSuccessful) {
                        mutableLiveDataGithubProfileDetail.postValue(null)
                        toastSingleLiveEvent.postValue(response.toString())
                    } else if (response.body() != null) {
                        val responseBody = response.body()!!

                        newGithubProfileDetailList.add(responseBody)
                        mutableLiveDataGithubProfileDetail.postValue(newGithubProfileDetailList)
                    }
                }

                override fun onFailure(call: Call<GithubProfileDetail?>, t: Throwable) {
                    Log.e("failure", t.toString())
                }
            })
        }
    }
}