package com.illicitintelligence.android.groupgithub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.model.GithubRepos;

import java.util.ArrayList;


public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private ArrayList<GithubRepos> repos;

    public RepoAdapter(ArrayList<GithubRepos> repos) {
        this.repos = repos;
    }


    @NonNull
    @Override
    public RepoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_repos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.ViewHolder holder, int position) {
       // Log.d("TAG_P", "onBindViewHolder: "+ Arrays.toString(user));
        holder.textViewUser.setText(repos.get(position).getOwner().getLogin());
        holder.textViewRepoName.setText(repos.get(position).getName());
    }

    @Override
    public int getItemCount() { return repos.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUser;
        TextView textViewRepoName;

           ViewHolder(@NonNull View itemView) {
               super(itemView);
               textViewUser = itemView.findViewById(R.id.username_textview);
               textViewRepoName = itemView.findViewById(R.id.repoName_textview);
           }
    }
}
