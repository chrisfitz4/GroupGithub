package com.illicitintelligence.android.groupgithub.model.commits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class File {

    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("additions")
    @Expose
    private Integer additions;
    @SerializedName("deletions")
    @Expose
    private Integer deletions;
    @SerializedName("changes")
    @Expose
    private Integer changes;
    @SerializedName("blob_url")
    @Expose
    private String blobUrl;
    @SerializedName("raw_url")
    @Expose
    private String rawUrl;
    @SerializedName("contents_url")
    @Expose
    private String contentsUrl;
    @SerializedName("patch")
    @Expose
    private String patch;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public File withSha(String sha) {
        this.sha = sha;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File withFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public File withStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getAdditions() {
        return additions;
    }

    public void setAdditions(Integer additions) {
        this.additions = additions;
    }

    public File withAdditions(Integer additions) {
        this.additions = additions;
        return this;
    }

    public Integer getDeletions() {
        return deletions;
    }

    public void setDeletions(Integer deletions) {
        this.deletions = deletions;
    }

    public File withDeletions(Integer deletions) {
        this.deletions = deletions;
        return this;
    }

    public Integer getChanges() {
        return changes;
    }

    public void setChanges(Integer changes) {
        this.changes = changes;
    }

    public File withChanges(Integer changes) {
        this.changes = changes;
        return this;
    }

    public String getBlobUrl() {
        return blobUrl;
    }

    public void setBlobUrl(String blobUrl) {
        this.blobUrl = blobUrl;
    }

    public File withBlobUrl(String blobUrl) {
        this.blobUrl = blobUrl;
        return this;
    }

    public String getRawUrl() {
        return rawUrl;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }

    public File withRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
        return this;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    public File withContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
        return this;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public File withPatch(String patch) {
        this.patch = patch;
        return this;
    }

}
