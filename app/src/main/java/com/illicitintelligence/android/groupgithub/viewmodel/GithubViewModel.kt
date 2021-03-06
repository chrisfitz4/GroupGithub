package com.illicitintelligence.android.groupgithub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.illicitintelligence.android.groupgithub.model.GithubRepos
import com.illicitintelligence.android.groupgithub.model.GithubUser
import com.illicitintelligence.android.groupgithub.model.commits.CommitResult
import com.illicitintelligence.android.groupgithub.network.GithubRetrofit
import com.illicitintelligence.android.groupgithub.network.UserAccessToken
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache

class GithubViewModel (application: Application) : AndroidViewModel(application) {

    private val githubRetrofit = GithubRetrofit(Cache(application.cacheDir, 5 * 1024 * 1024))
    private val compositeDisposable = CompositeDisposable()


    fun getUser(username: String): Observable<GithubUser>? {
        return githubRetrofit.getUser(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRepos(username: String): Observable<List<GithubRepos>>? {
        return githubRetrofit.getRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRepos(userAccessToken: UserAccessToken): Observable<List<GithubRepos>>? {
        return githubRetrofit.getRepos(userAccessToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCommits(username: String, repoName: String): Observable<List<CommitResult>>{
        return githubRetrofit.getCommit(username,repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}