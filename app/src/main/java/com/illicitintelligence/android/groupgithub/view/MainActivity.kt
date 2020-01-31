package com.illicitintelligence.android.groupgithub.view

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.illicitintelligence.android.groupgithub.BuildConfig
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.illicitintelligence.android.groupgithub.R
import com.illicitintelligence.android.groupgithub.adapter.RepoAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.illicitintelligence.android.groupgithub.model.GithubRepos
import com.illicitintelligence.android.groupgithub.util.Constants
import com.illicitintelligence.android.groupgithub.network.UserAccessToken
import com.illicitintelligence.android.groupgithub.viewmodel.GithubViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RepoAdapter.OpenCommitsDelegate,
    UserFrag.ColorChangedDelegate {

    lateinit var viewModel: GithubViewModel
    val TAG = "TAG_X"
    val compositeDisposable = CompositeDisposable()
    lateinit var rvAdapter: RepoAdapter
    var repoList = ArrayList<GithubRepos>()
    var arrayList = ArrayList<String>()

    lateinit var sharedPreferences: SharedPreferences

    val loginFragment = LoginFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAdapter = RepoAdapter(ArrayList<GithubRepos>(), this, this)

        viewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        setUpRV()

        sharedPreferences =
            getSharedPreferences(Constants.DUMMY_SHAREDPREFERENCES, Context.MODE_PRIVATE)
        if (sharedPreferences.getString(Constants.DUMMY_SHAREDPREFERENCES_KEY, "").equals("")) {

            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.expand_center,R.anim.collapse_center,R.anim.expand_center,R.anim.collapse_center)
                .addToBackStack(loginFragment.tag)
                .add(R.id.frameRV,loginFragment)
                .commit()
        } else {
            setUpSplashScreen()
            var repoUsers: String? =
                sharedPreferences.getString(Constants.DUMMY_SHAREDPREFERENCES_KEY, "")
            var userNames = repoUsers?.split(",")
            if (userNames != null) {
                for(item in userNames){
                    Log.d(TAG,item+"----->"+item.split(".")[0])
                    arrayList.add(item.split(".")[0])
                }
            }
            for(item in arrayList){
                getReposForUser(item)
            }
        }
    }

    fun getReposForUser(user: String) {
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

    public fun removeLoginFragment() {
        supportFragmentManager.beginTransaction()
            .remove(loginFragment)
            .commit()
    }


    public fun onClickUser(view: View) {
        val userFrag = UserFrag(this)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.expand_topright,
                R.anim.collapse_topright,
                R.anim.expand_topright,
                R.anim.collapse_topright
            )
            .addToBackStack(userFrag.tag)
            .add(R.id.frameRV, userFrag)
            .commit()
    }

    private fun setUpRV() {
        rv_main.adapter = rvAdapter
        rv_main.layoutManager = LinearLayoutManager(this)
    }

    override fun getCommits(repo: GithubRepos?) {
        val commitFragment = CommitsFrag(repo)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.expand_center,
                R.anim.collapse_center,
                R.anim.expand_center,
                R.anim.collapse_center
            )
            .add(R.id.frameRV, commitFragment)
            .addToBackStack(commitFragment.tag)
            .commit()
    }

    override fun rereadSharedPreferences() {
        rvAdapter.readSharedPreferences()
        rvAdapter.notifyDataSetChanged()
    }

    override fun notifyDeletionFromSharedP(user: String?) {
        Log.d("TAG_X",user)
        for(item in repoList){
//            if(user.equals(item.owner.login)){
//                repoList.remove(item)
//            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}
