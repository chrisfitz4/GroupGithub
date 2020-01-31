package com.illicitintelligence.android.groupgithub.network

import android.app.IntentService
import android.content.Intent
import com.illicitintelligence.android.groupgithub.model.GithubRepos
import com.illicitintelligence.android.groupgithub.model.GithubUser
import com.illicitintelligence.android.groupgithub.model.commits.CommitResult
import com.illicitintelligence.android.groupgithub.util.Constants
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubRetrofit(val cache: Cache) {


    var githubService: GithubService = getGithubService(getRetrofit())

    private val httpClient: OkHttpClient? = null
    private val cacheSize = (10 * 1024 * 1024).toLong()

    private fun getRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    private fun getGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    fun getUser(userName: String): Observable<GithubUser> {
        return githubService.getUser(userName)
    }

    fun getRepos(userName: String): Observable<List<GithubRepos>> {
        return githubService.getRepos(userName)
    }

    fun getRepos(userAccessToken: UserAccessToken): Observable<List<GithubRepos>> {
        return githubService.getReposWithToken(userAccessToken.accessToken, userAccessToken.userName)
    }

    fun getCommit(userName: String, repoName: String): Observable<List<CommitResult>> {
        return githubService.getCommit(userName, repoName)
    }

    fun getCommitDetail(sha: String, userName: String, repoName: String): Observable<CommitResult> {
        return githubService.getCommitDetail(sha, userName, repoName)
    }
}