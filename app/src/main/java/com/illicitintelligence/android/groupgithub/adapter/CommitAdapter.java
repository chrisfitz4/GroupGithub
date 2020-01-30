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

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.ViewHolder> {

    private ArrayList<CommitResult> commits;

    public CommitAdapter(ArrayList<CommitResult> commits) {
        this.commits = commits;
    }

    @NonNull
    @Override
    public CommitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_user, parent, false);
        return new CommitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commits.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
