package com.illicitintelligence.android.groupgithub.network

import com.illicitintelligence.android.groupgithub.model.GithubRepos
import com.illicitintelligence.android.groupgithub.model.GithubUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GithubService {

    @GET("users/{userName}/repos")
    fun getReposWithToken(
        @Header("Authorization") accessToken: String,
        @Path("userName") userName: String): Observable<List<GithubRepos>>

    @GET("users/{userName}/repos")
    fun getRepos(
        @Path("userName") userName: String): Observable<List<GithubRepos>>

    @GET("users/{userName}")
    fun getUser(
        @Path("userName") userName: String): Observable<GithubUser>

}