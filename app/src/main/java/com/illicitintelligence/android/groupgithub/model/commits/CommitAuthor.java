package com.illicitintelligence.android.groupgithub.model.commits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.illicitintelligence.android.groupgithub.model.GithubUser;

public class CommitAuthor {


    @SerializedName("name")
    @Expose
    private String author;
    @SerializedName("email")
    @Expose
    private String committer;
    @SerializedName("date")
    @Expose
    private String message;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
