package com.nerisa.ajaibtestapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nerisa.ajaibtestapplication.R
import com.nerisa.ajaibtestapplication.adapters.GithubProfileSearchAdapter
import com.nerisa.ajaibtestapplication.cores.LoadingDialog
import com.nerisa.ajaibtestapplication.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var githubProfileSearchAdapter: GithubProfileSearchAdapter

    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingDialog = LoadingDialog(this)

        val decorator = DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL)
        decorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(decorator)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.visibility = View.GONE

        mainActivityViewModel.mutableLiveDataGithubProfileSearchData.observe(this, Observer {
            if(it == null|| it.isEmpty()){
                recyclerView.visibility = View.GONE
                Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show()
            }else{
                if (recyclerView.visibility != View.VISIBLE) {
                    recyclerView.visibility = View.VISIBLE
                }

                mainActivityViewModel.callSearchUserDetailAPI(it)
            }
        })

        mainActivityViewModel.mutableLiveDataGithubProfileDetail.observe(this, Observer {
            if(it == null){
                recyclerView.adapter = null
                recyclerView.visibility = View.GONE
            }else{
                githubProfileSearchAdapter = GithubProfileSearchAdapter(this@MainActivity, it)
                recyclerView.adapter = githubProfileSearchAdapter
            }

            loadingDialog.dismissDialog()
        })

        mainActivityViewModel.toastSingleLiveEvent.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

            loadingDialog.dismissDialog()
        })

        editSearch.requestFocus()
        editSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadingDialog.show()
                mainActivityViewModel.callSearchUserAPI(editSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

    }
}