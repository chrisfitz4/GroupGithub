package com.illicitintelligence.android.groupgithub.adapter;

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

public class RecyclerViewNewUserAdapter extends RecyclerView.Adapter<RecyclerViewNewUserAdapter.ViewHolder> {

    ArrayList<String> newusers;

    public RecyclerViewNewUserAdapter(ArrayList<String> newusers) {
        this.newusers = newusers;
    }

    @NonNull
    @Override
    public RecyclerViewNewUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_adduser, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewNewUserAdapter.ViewHolder holder, int position) {

        String[] newuserss = newusers.get(position).split(",");

        Log.d("TAG_P", "onBindViewHolder: "+ Arrays.toString(newuserss));
        holder.textViewnewusers.setText(newuserss[0]);

    }

    @Override
    public int getItemCount() {
        return newusers.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewnewusers;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewnewusers = itemView.findViewById(R.id.addusers_rvtxt);
        }
    }

}