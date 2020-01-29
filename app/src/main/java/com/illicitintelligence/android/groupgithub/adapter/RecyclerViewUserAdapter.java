package com.illicitintelligence.android.groupgithub.adapter;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;

import java.util.ArrayList;
import java.util.Arrays;


public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.ViewHolder> {

    ArrayList<String> users;

    public RecyclerViewUserAdapter(ArrayList<String> users) {
        this.users = users;
    }


    @NonNull
    @Override
    public RecyclerViewUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_user, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewUserAdapter.ViewHolder holder, int position) {

    String[] user = users.get(position).split(",");
        Log.d("TAG_P", "onBindViewHolder: "+ Arrays.toString(user));
        holder.textViewuser.setText(user[0]);


    }

    @Override
    public int getItemCount() { return users.size(); }

       public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewuser;

           public ViewHolder(@NonNull View itemView) {
               super(itemView);
               textViewuser = itemView.findViewById(R.id.users_rvtxt);



           }


    }
}
