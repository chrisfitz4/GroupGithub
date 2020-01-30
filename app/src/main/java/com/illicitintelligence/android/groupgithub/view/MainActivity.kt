package com.illicitintelligence.android.groupgithub.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.illicitintelligence.android.groupgithub.R
import com.illicitintelligence.android.groupgithub.adapter.RepoAdapter
import com.illicitintelligence.android.groupgithub.model.GithubRepos
import com.illicitintelligence.android.groupgithub.viewmodel.GithubViewModel
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RepoAdapter.OpenCommitsDelegate {

    lateinit var viewModel: GithubViewModel

    val compositeDisposable = CompositeDisposable()
    lateinit var rvAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAdapter = RepoAdapter(ArrayList<GithubRepos>(),this, this)
        //setUpSplashScreen()

        viewModel = ViewModelProviders.of(this).get(GithubViewModel::class.java)
        setUpRV()

        //todo: replace username with the values from SharedPreferences

        viewModel.getRepos("chrisfitz4")?.subscribe {
            rvAdapter =
                RepoAdapter(it as ArrayList<GithubRepos>, this, this)
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
  