
package com.illicitintelligence.android.groupgithub.model.commits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.illicitintelligence.android.groupgithub.model.GithubUser;

public class Commit {

    @SerializedName("author")
    @Expose
    private CommitAuthor author;
    @SerializedName("committer")
    @Expose
    private CommitAuthor committer;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tree")
    @Expose
    private Tree tree;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("verification")
    @Expose
    private Verification verification;

    public CommitAuthor getAuthor() {
        return author;
    }

    public void setGithubUser(CommitAuthor author) {
        this.author = author;
    }

    public CommitAuthor getCommitter() {
        return committer;
    }

    public void setCommitter(CommitAuthor committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

}
