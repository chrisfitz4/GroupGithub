package com.illicitintelligence.android.groupgithub.adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.illicitintelligence.android.groupgithub.model.AppUser;
import com.illicitintelligence.android.groupgithub.model.GithubRepos;
import com.illicitintelligence.android.groupgithub.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;


public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private final String TAG = "TAG_X";

    private final int CUTOFF_LENGTH_SMALL = 15;
    private final int CUTOFF_LENGTH_MEDIUM = 20;
    private ArrayList<GithubRepos> repos;
    private Context context;
    private OpenCommitsDelegate delegate;
    SharedPreferences sharedPreferences;
    HashMap<String,Integer> appUsers = new HashMap<>();

    public interface OpenCommitsDelegate{
        void getCommits(GithubRepos repo);
    }

    public RepoAdapter(ArrayList<GithubRepos> repos, Context context, OpenCommitsDelegate delegate) {
        this.repos = repos;
        this.context = context;
        this.delegate = delegate;
        readSharedPreferences();
    }

    public void readSharedPreferences() {
        this.sharedPreferences = context.getSharedPreferences(Constants.DUMMY_SHAREDPREFERENCES,Context.MODE_PRIVATE);
        String users = sharedPreferences.getString(Constants.DUMMY_SHAREDPREFERENCES_KEY,"");
        Log.d(TAG, "readSharedPreferences: "+users);
        if(users.equals("")){
            return;
        }
        if(users.charAt(0)==','){
            users=users.substring(1);
        }
        if(users.charAt(users.length()-1)==','){
            users=users.substring(0,users.length()-1);
        }
        if(users.length()!=0){
            String[] userList = users.split(",");
            Log.d(TAG, "RepoAdapter: "+users);
            for(int i = 0; i<userList.length;i++){
                String[] oneUser = userList[i].split("\\.");
                appUsers.put(oneUser[0],Integer.parseInt(oneUser[1]));
                Log.d(TAG, "RepoAdapter check: "+oneUser[0]+"  ,  "+oneUser[1]);
            }
        }
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
        Glide.with(context).load(repos.get(position).getOwner().getAvatarUrl()).into(holder.userIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG_X", repos.size() + "");
                delegate.getCommits(repos.get(position));
            }
        });
        try {
            holder.itemView.getBackground().setTint(appUsers.get(repos.get(position).getOwner().getLogin()));
        }catch(NullPointerException n){
            holder.itemView.getBackground().setTint(context.getColor(R.color.dandelion));
            Log.e(TAG, "onBindViewHolder: "+n.getMessage());
        }
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
