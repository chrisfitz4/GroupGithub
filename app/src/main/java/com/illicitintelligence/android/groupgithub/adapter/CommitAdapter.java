package com.illicitintelligence.android.groupgithub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.model.commits.CommitResult;

import java.util.ArrayList;
import java.util.List;

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.ViewHolder> {

    private List<CommitResult> commits;

    public CommitAdapter(List<CommitResult> commits) {
        this.commits = commits;
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
        holder.author.setText(currentCommit.getAuthor().getLogin());
        holder.description.setText(currentCommit.getCommit().getMessage());
        holder.date.setText(currentCommit.getCommit().getAuthor().getMessage());
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
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.commit_author_tv);
            date = itemView.findViewById(R.id.commit_date_tv);
            description = itemView.findViewById(R.id.commit_description_tv);
            pluses = itemView.findViewById(R.id.commit_pluses_tv);
            minuses = itemView.findViewById(R.id.commit_minuses_tv);
        }
    }
}
