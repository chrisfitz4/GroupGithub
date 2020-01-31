package com.illicitintelligence.android.groupgithub.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.model.commits.CommitResult;

import java.util.ArrayList;
import java.util.List;

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.ViewHolder> {

    private List<CommitResult> commits;
    private NavigationDelegate navigationDelegate;

    public interface NavigationDelegate{
        void navigate(String url);
    }

    public CommitAdapter(List<CommitResult> commits, NavigationDelegate context) {
        this.commits = commits;
        this.navigationDelegate = context;
    }

    public void setCommits(List<CommitResult> commits) {
        this.commits = commits;
    }

    @NonNull
    @Override
    public CommitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_commits, parent, false);
        return new CommitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitAdapter.ViewHolder holder, int position) {
        CommitResult currentCommit = commits.get(position);
        if(currentCommit.getAuthor() != null)
            holder.author.setText(currentCommit.getAuthor().getLogin());
        else
            holder.author.setText("No author");

        holder.description.setText(currentCommit.getCommit().getMessage());
        String date = currentCommit.getCommit().getAuthor().getMessage();
        String[] dateSplit = date.split("T");
        holder.date.setText(dateSplit[0]);
        holder.navigate.setOnClickListener(view->{
            navigationDelegate.navigate(commits.get(position).getHtmlUrl());
        });
    }

    @Override
    public int getItemCount() {
        return commits.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView date;
        TextView description;
        TextView pluses;
        TextView minuses;
        ImageView navigate;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.commit_author_tv);
            date = itemView.findViewById(R.id.commit_date_tv);
            description = itemView.findViewById(R.id.commit_description_tv);
            pluses = itemView.findViewById(R.id.commit_pluses_tv);
            minuses = itemView.findViewById(R.id.commit_minuses_tv);
            navigate = itemView.findViewById(R.id.navigation_rv_commits);
        }
    }
}
