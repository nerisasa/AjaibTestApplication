package com.nerisa.ajaibtestapplication.adapters

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nerisa.ajaibtestapplication.R
import com.nerisa.ajaibtestapplication.activities.ProfileDetailActivity
import com.nerisa.ajaibtestapplication.models.GithubProfileDetail
import kotlinx.android.synthetic.main.row_profile.view.*

class GithubProfileSearchAdapter(private val context: Context, private val githubProfiles: MutableList<GithubProfileDetail>) : RecyclerView.Adapter<GithubProfileSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_profile, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return githubProfiles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val githubProfile = githubProfiles[position]!!

        //photo
        Glide.with(holder.itemView.context)
            .load(githubProfile.avatarUrl)
            .into(holder.user_photo)

        //text
        holder.text_fullname.text = githubProfile.name
        holder.text_username.text = "@" + githubProfile.login
        holder.text_user_occupation.text = githubProfile.occupation
        holder.text_user_location.text = githubProfile.location
        holder.text_user_email.text = githubProfile.email

        holder.row.setOnClickListener {
            val intent = Intent(context, ProfileDetailActivity::class.java)
            intent.putExtra("avatarUrl", githubProfile.avatarUrl)
            intent.putExtra("name", githubProfile.name)
            intent.putExtra("login", githubProfile.login)
            intent.putExtra("occupation", githubProfile.occupation)
            intent.putExtra("location", githubProfile.location)
            intent.putExtra("email", githubProfile.email)
            intent.putExtra("followers", githubProfile.followers)
            intent.putExtra("following", githubProfile.following)

            context.startActivity(intent)
        }

        if((position + 1) == githubProfiles.size){
            holder.divider.visibility = View.GONE
        }
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view){
        val row = view.row
        val user_photo = view.user_photo
        val text_fullname = view.text_fullname
        val text_username = view.text_username
        val text_user_occupation = view.text_user_occupation
        val text_user_location = view.text_user_location
        val text_user_email = view.text_user_email
        val divider = view.divider
    }
}