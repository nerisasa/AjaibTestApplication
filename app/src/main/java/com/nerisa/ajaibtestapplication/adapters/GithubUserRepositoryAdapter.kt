package com.nerisa.ajaibtestapplication.adapters

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nerisa.ajaibtestapplication.R
import com.nerisa.ajaibtestapplication.activities.ProfileDetailActivity
import com.nerisa.ajaibtestapplication.models.GithubProfileDetail
import com.nerisa.ajaibtestapplication.models.GithubProfileSearchData
import com.nerisa.ajaibtestapplication.models.GithubRepository
import com.nerisa.ajaibtestapplication.models.GithubUserRepositorySearchData
import com.nerisa.ajaibtestapplication.repositories.ProfileDetailRepository
import kotlinx.android.synthetic.main.row_profile.view.*
import kotlinx.android.synthetic.main.row_profile.view.user_photo
import kotlinx.android.synthetic.main.row_repository.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GithubUserRepositoryAdapter(private val context: Context, private val githubUserRepository: MutableList<GithubRepository>) : RecyclerView.Adapter<GithubUserRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_repository, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return githubUserRepository.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val githubUserRepository = githubUserRepository[position]!!

        //photo
        Glide.with(holder.itemView.context)
            .load(githubUserRepository.photo)
            .into(holder.user_photo)

        //text
        holder.text_repository_title.text = githubUserRepository.title
        holder.text_repository_details.text = githubUserRepository.details
        holder.text_repository_favorites.text = githubUserRepository.watchers

        var lastUpdate = githubUserRepository.last_update!!.replace("T", " ").replace("Z", "")

        holder.text_repository_last_update.text = lastUpdate
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view){
        val user_photo = view.user_photo
        val text_repository_title = view.text_repository_title
        val text_repository_details = view.text_repository_details
        val text_repository_favorites = view.text_repository_favorites
        val text_repository_last_update = view.text_repository_last_update
    }
}