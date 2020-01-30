package com.illicitintelligence.android.groupgithub.network

import org.junit.Test

import org.junit.Assert.*
import org.junit.experimental.results.ResultMatchers.isSuccessful
import java.io.IOException


class GithubRetrofitTest {

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

    @Test
    fun getRepos() {
    }

    @Test
    fun getRepos1() {
        val githubRetrofit = GithubRetrofit()
        val repos = githubRetrofit.getRepos(UserAccessToken("c93c9d20489f56dda16d4be219df91916ad621e7","chrisfitz4"))

        try {
            assertNotNull(repos.blockingFirst())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun getCommit() {
        val githubRetrofit = GithubRetrofit()
        val commits = githubRetrofit.getCommit("chrisfitz4","groupgithub")

        try {
            assertNotNull(commits.blockingFirst())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}