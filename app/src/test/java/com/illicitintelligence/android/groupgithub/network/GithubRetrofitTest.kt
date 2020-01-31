package com.illicitintelligence.android.groupgithub.network

import okhttp3.Cache
import org.junit.Test

import org.junit.Assert.*
import org.junit.experimental.results.ResultMatchers.isSuccessful
import java.io.File
import java.io.IOException


class GithubRetrofitTest {

    @Test
    fun getUser() {
        val githubRetrofit = GithubRetrofit(Cache(File("blank"),10))
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
        val githubRetrofit = GithubRetrofit(Cache(File("blank"),10))
        val repos = githubRetrofit.getRepos(UserAccessToken())

        try {
            assertNotNull(repos.blockingFirst())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun getCommit() {
        val githubRetrofit = GithubRetrofit(Cache(File("blank"),10))
        val commits = githubRetrofit.getCommit("chrisfitz4","groupgithub")

        try {
            assertNotNull(commits.blockingFirst())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun getCommitDetail() {
        val githubRetrofit = GithubRetrofit(Cache(File("blank"),10))
        val commits = githubRetrofit.getCommitDetail("e636f4ee5542ca07564080a074fa2e90576e2e5c","chrisfitz4","groupgithub")

        try {
            assertNotNull(commits.blockingFirst())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}