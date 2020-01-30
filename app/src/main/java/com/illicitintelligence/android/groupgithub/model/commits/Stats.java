package com.illicitintelligence.android.groupgithub.model.commits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("additions")
    @Expose
    private Integer additions;
    @SerializedName("deletions")
    @Expose
    private Integer deletions;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Stats withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getAdditions() {
        return additions;
    }

    public void setAdditions(Integer additions) {
        this.additions = additions;
    }

    public Stats withAdditions(Integer additions) {
        this.additions = additions;
        return this;
    }

    public Integer getDeletions() {
        return deletions;
    }

    public void setDeletions(Integer deletions) {
        this.deletions = deletions;
    }

    public Stats withDeletions(Integer deletions) {
        this.deletions = deletions;
        return this;
    }

}

