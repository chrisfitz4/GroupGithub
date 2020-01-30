package com.illicitintelligence.android.groupgithub.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.model.GithubUser;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<GithubUser> users;

    public UserAdapter(ArrayList<GithubUser> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

       // Log.d("TAG_P", "onBindViewHolder: "+ Arrays.toString(newuserss));
        holder.textViewnewusers.setText(users.get(position).getLogin());
    }

    @Override
    public int getItemCount() {
        return users.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewnewusers;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewnewusers = itemView.findViewById(R.id.users_rvtxt);
        }
    }

}