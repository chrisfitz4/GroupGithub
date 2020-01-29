package com.illicitintelligence.android.groupgithub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.illicitintelligence.android.groupgithub.network.GithubRetrofit
import com.illicitintelligence.android.groupgithub.network.UserAccessToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GithubViewModel (application: Application) : AndroidViewModel(application) {

    private val githubRetrofit = GithubRetrofit()
    private val compositeDisposable = CompositeDisposable()

    fun getUser(username: String) {
        compositeDisposable.add(
            githubRetrofit.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gitUSer ->

                })
    }

    fun getRepos(username: String) {
        compositeDisposable.add(
            githubRetrofit.getRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->

                })
    }

    fun getRepos(userAccessToken: UserAccessToken) {
        compositeDisposable.add(
            githubRetrofit.getRepos(userAccessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->

                })
    }

}