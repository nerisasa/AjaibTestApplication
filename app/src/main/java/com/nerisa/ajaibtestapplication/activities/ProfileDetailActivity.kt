package com.nerisa.ajaibtestapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.nerisa.ajaibtestapplication.R
import com.nerisa.ajaibtestapplication.adapters.GithubUserRepositoryAdapter
import com.nerisa.ajaibtestapplication.cores.LoadingDialog
import com.nerisa.ajaibtestapplication.viewmodels.ProfileDetailViewModel
import kotlinx.android.synthetic.main.activity_user_detail.*

class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var profileDetailViewModel: ProfileDetailViewModel
    private lateinit var githubUserRepositoryAdapter: GithubUserRepositoryAdapter

    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        loadingDialog = LoadingDialog(this)
        loadingDialog.show()

        Glide.with(this)
            .load(intent.getStringExtra("avatarUrl"))
            .into(user_photo)

        text_fullname.text = intent.getStringExtra("name")
        text_username.text = intent.getStringExtra("login")
        text_user_occupation.text = intent.getStringExtra("occupation")
        text_user_followers.text = intent.getStringExtra("followers") + " Followers"
        text_user_following.text = intent.getStringExtra("following") + " Following"
        text_user_location.text = intent.getStringExtra("location")
        text_user_email.text = intent.getStringExtra("email")

        profileDetailViewModel = ViewModelProvider(this).get(ProfileDetailViewModel::class.java)

        recyclerViewRepository.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        profileDetailViewModel.mutableLiveDataGithubRepository.observe(this, Observer {
            if(it == null){
                recyclerViewRepository.adapter = null
            }else{
                githubUserRepositoryAdapter = GithubUserRepositoryAdapter(this, it)
                recyclerViewRepository.adapter = githubUserRepositoryAdapter
            }

            loadingDialog.dismissDialog()
        })

        profileDetailViewModel.callUserRepositoryAPI(intent.getStringExtra("avatarUrl")!!, intent.getStringExtra("login")!!)
    }
}