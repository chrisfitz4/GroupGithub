package com.illicitintelligence.android.groupgithub.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.model.GithubRepos;

import java.util.ArrayList;


public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private final int CUTOFF_LENGTH_SMALL = 16;
    private final int CUTOFF_LENGTH_MEDIUM = 20;
    private ArrayList<GithubRepos> repos;
    private Context context;
    private OpenCommitsDelegate delegate;

    public interface OpenCommitsDelegate{
        void getCommits(GithubRepos repo);
    }

    public RepoAdapter(ArrayList<GithubRepos> repos, Context context, OpenCommitsDelegate delegate) {
        this.repos = repos;
        this.context = context;
        this.delegate = delegate;
    }


    @NonNull
    @Override
    public RepoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout_repos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.ViewHolder holder, final int position) {
       // Log.d("TAG_P", "onBindViewHolder: "+ Arrays.toString(user));
        holder.textViewUser.setText(repos.get(position).getOwner().getLogin());
        holder.textViewRepoName.setText(repos.get(position).getName());
        holder.language.setText(repos.get(position).getLanguage());
        if(repos.get(position).getName().length()>CUTOFF_LENGTH_MEDIUM){
            holder.textViewRepoName.setText(wrappingHelper(repos.get(position).getName()));
            holder.textViewRepoName.setTextSize(18);
        }else if(repos.get(position).getName().length()>CUTOFF_LENGTH_SMALL){
            holder.textViewRepoName.setTextSize(18);
        }else{
            holder.textViewRepoName.setTextSize(24);
        }
        Glide.with(context).load(repos.get(position).getOwner().getAvatarUrl()).into(holder.userIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG_X", repos.size() + "");
                delegate.getCommits(repos.get(position));
            }
        });
    }

    private String wrappingHelper(String toWrap){
        String toReturn = toWrap;
        if(toWrap.contains("_")){
            for(int i = toWrap.length()-1; i>0; i--){
                if(toWrap.charAt(i)=='_'||toWrap.charAt(i)=='-'){
                    toReturn = "";
                    toReturn+=toWrap.substring(0,i)+"\n "+toWrap.substring(i);
                    Log.d("TAG_X", "wrappingHelper: "+toReturn);
                    break;
                }
            }
            return toReturn;
        }
        return toReturn;
    }

    @Override
    public int getItemCount() { return repos.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUser;
        TextView textViewRepoName;
        ImageView userIcon;
        TextView language;

           ViewHolder(@NonNull View itemView) {
               super(itemView);
               textViewUser = itemView.findViewById(R.id.username_textview);
               textViewRepoName = itemView.findViewById(R.id.repoName_textview);
               userIcon = itemView.findViewById(R.id.user_icon);
               language = itemView.findViewById(R.id.textview_language);
           }
    }
}
