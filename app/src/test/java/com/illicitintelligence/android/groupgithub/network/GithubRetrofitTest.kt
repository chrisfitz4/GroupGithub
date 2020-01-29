package com.illicitintelligence.android.groupgithub.network

import org.junit.Test

import org.junit.Assert.*
import org.junit.experimental.results.ResultMatchers.isSuccessful
import java.io.IOException


class GithubRetrofitTest {

    @Test
    fun getGithubService() {
    }

    @Test
    fun setGithubService() {
    }

    @Test
    fun getUser() {
        val githubRetrofit = GithubRetrofit()
        val user = githubRetrofit.getUser("trung-luu-enhance")

        try {
            assertNotNull(user.blockingFirst())
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}