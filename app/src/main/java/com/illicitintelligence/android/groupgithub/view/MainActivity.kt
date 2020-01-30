package com.illicitintelligence.android.groupgithub.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.illicitintelligence.android.groupgithub.R
import com.illicitintelligence.android.groupgithub.adapter.RepoAdapter
import com.illicitintelligence.android.groupgithub.model.GithubRepos
import com.illicitintelligence.android.groupgithub.util.Constants
import com.illicitintelligence.android.groupgithub.viewmodel.GithubViewModel
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RepoAdapter.OpenCommitsDelegate {

    lateinit var viewModel: GithubViewModel
    val TAG = "TAG_X"
    val compositeDisposable = CompositeDisposable()
    lateinit var rvAdapter: RepoAdapter
    var repoList = ArrayList<GithubRepos>()

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(Constants.DUMMY_SHAREDPREFERENCES, Context.MODE_PRIVATE)
        if(sharedPreferences.getString(Constants.DUMMY_SHAREDPREFERENCES_KEY,"").equals("")){
            val editor = sharedPreferences.edit()
            var chrisAndTrung: String = ""
            chrisAndTrung+="chrisfitz4"
            chrisAndTrung+=Constants.DUMMY_SHAREDPREFERENCES_REGEX_TWO
            chrisAndTrung+= Color.CYAN
            Log.d(TAG, ""+Color.CYAN)
            chrisAndTrung+=Constants.DUMMY_SHAREDPREFERENCES_REGEX
            chrisAndTrung+="trung-luu-enhance"
            chrisAndTrung+=Constants.DUMMY_SHAREDPREFERENCES_REGEX_TWO
            chrisAndTrung+= Color.GREEN
            editor.putString(Constants.DUMMY_SHAREDPREFERENCES_KEY,chrisAndTrung)
            editor.apply()
            editor.clear()
        }

        rvAdapter = RepoAdapter(ArrayList<GithubRepos>(),this, this)
        //setUpSplashScreen()

        viewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        setUpRV()

        //todo: replace username with the values from SharedPreferences

        getReposForUser("chrisfitz4")
        getReposForUser("trung-luu-enhance")
    }

    private fun getReposForUser(user: String) {
        viewModel.getRepos(user)?.subscribe {
            if (repoList.size == 0) {
                repoList = it as ArrayList<GithubRepos>
                rvAdapter = RepoAdapter(repoList, this, this)
                rv_main.adapter = rvAdapter
            } else {
                repoList.addAll(it as ArrayList<GithubRepos>)
                repoList.sort()
                rvAdapter.notifyDataSetChanged()
            }
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
    }
    private fun setUpRV(){
        rv_main.adapter = rvAdapter
        rv_main.layoutManager = LinearLayoutManager(this)
    }

    override fun getCommits(repo: GithubRepos?) {
        val commitFragment = CommitsFrag(repo)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.expand_center,R.anim.collapse_center,R.anim.expand_center,R.anim.collapse_center)
            .add(R.id.frameRV,commitFragment)
            .addToBackStack(commitFragment.tag)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
  