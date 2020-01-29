package com.illicitintelligence.android.groupgithub.view

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.illicitintelligence.android.groupgithub.BuildConfig
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.illicitintelligence.android.groupgithub.R
import com.illicitintelligence.android.groupgithub.network.OAuthenticationInstance
import kotlinx.android.synthetic.main.activity_main.*
import com.illicitintelligence.android.groupgithub.adapter.RecyclerViewNewUserAdapter
import com.illicitintelligence.android.groupgithub.adapter.RecyclerViewUserAdapter
import com.illicitintelligence.android.groupgithub.model.GithubRepos
import com.illicitintelligence.android.groupgithub.viewmodel.GithubViewModel
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GithubViewModel

    val compositeDisposable = CompositeDisposable()
    var rvAdapter = RecyclerViewUserAdapter(ArrayList<GithubRepos>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setUpSplashScreen()

        viewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        setUpRV()
        viewModel.getRepos("chrisfitz4")?.subscribe {
            rvAdapter = RecyclerViewUserAdapter(it as ArrayList<GithubRepos>)
            rv_main.adapter = rvAdapter
        }?.let { compositeDisposable.add(it) }
    }

    private fun setUpSplashScreen() {
        val splashFragment = SplashFragment()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                0,
                R.anim.collapse_center,
                R.anim.expand_center,
                R.anim.collapse_center
            )
            .addToBackStack(splashFragment.tag)
            .add(R.id.frameRV, splashFragment)
            .commit()

        next_userbtn.setOnClickListener {
            val login = LoginFragment()
            supportFragmentManager.beginTransaction()
                .addToBackStack(login.tag)
                .add(R.id.frameRV, login)
                .commit()
        }
    }

    private fun setUpRV(){
        rv_main.adapter = rvAdapter
        rv_main.layoutManager = LinearLayoutManager(this)
    }
    private fun updateRV(){
        rvAdapter.notifyDataSetChanged()
    }
}

    override fun onResume() {
        super.onResume()

        val oauth = OAuthenticationInstance()

        val uri : Uri? = this.intent.data

        if(uri.toString().startsWith(BuildConfig.clientCallback)) {
            val code : String? = uri?.getQueryParameter("code")


        }

    }
}
