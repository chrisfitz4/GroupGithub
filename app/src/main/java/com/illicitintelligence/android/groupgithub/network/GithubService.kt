package com.illicitintelligence.android.groupgithub.network

import com.illicitintelligence.android.groupgithub.model.GithubUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GithubService {

    @GET("users/{userName}")
    fun getUserWithToken(
        @Header("Authorization") accessToken: String,
        @Path("userName") userName: String): Observable<GithubUser>

    @GET("users/{userName}")
    fun getUser(
        @Path("userName") userName: String): Observable<GithubUser>

}