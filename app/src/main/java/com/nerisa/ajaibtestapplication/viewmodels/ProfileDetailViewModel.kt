package com.nerisa.ajaibtestapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nerisa.ajaibtestapplication.models.*
import com.nerisa.ajaibtestapplication.repositories.ProfileDetailRepository
import org.json.JSONArray
import org.json.JSONObject

class ProfileDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    var mutableLiveDataGithubRepository = MutableLiveData<MutableList<GithubRepository>>()
    var newGithubRepository = mutableListOf<GithubRepository>()

    init {
        mutableLiveDataGithubRepository = MutableLiveData()
    }

    fun callUserRepositoryAPI(photo:String, username: String){
        newGithubRepository = mutableListOf()
        val profileDetailRepository = ProfileDetailRepository()

        profileDetailRepository.requestGetUserRepository(context, username, object: ProfileDetailRepository.ResponseHandler {
            override fun onSuccess(response: JSONObject) {}

            override fun onSuccessArray(response: JSONArray) {
                for (i in 0 until response.length()){
                    val jsonObject = response.getJSONObject(i)
                    val githubRepository = GithubRepository()

                    githubRepository.photo = photo
                    githubRepository.title = jsonObject.getString("name")
                    githubRepository.details = jsonObject.getString("description")
                    githubRepository.watchers = jsonObject.getString("watchers")
                    githubRepository.last_update = jsonObject.getString("updated_at")

                    newGithubRepository.add(githubRepository)
                }

                mutableLiveDataGithubRepository.postValue(newGithubRepository)

            }

            override fun onFailure(errorMessage: String) {
                mutableLiveDataGithubRepository.postValue(null)
            }

        }, "callUserRepositoryAPI error")
    }
}