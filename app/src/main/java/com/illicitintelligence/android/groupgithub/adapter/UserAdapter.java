package com.illicitintelligence.android.groupgithub.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.model.GithubUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<String> users;
    ChooseUserDelegate delegate;

    public interface ChooseUserDelegate{
        void clickUser(String username);
    }

    public UserAdapter(ArrayList<String> users, ChooseUserDelegate delegate) {
        this.users = users;
        this.delegate = delegate;
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
        String[] oneUser = users.get(position).split("\\.");
        Log.d("TAG_X", "onBindViewHolder: "+users.get(position));
        holder.textViewnewusers.setText(oneUser[0]);
        holder.itemView.getBackground().setTint(Integer.parseInt(oneUser[1]));
        holder.itemView.setOnClickListener(view->{
            delegate.clickUser(users.get(position));
        });
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

    public String removeItem(int position){
        Log.d("TAG_X", "removeItem: "+position);
        return users.remove(position);
    }

    public void setUsers(ArrayList<String> users){
        this.users = users;
    }

    public void setUsers(String[] users){
        this.users = Arrays.asList(users);
    }

}