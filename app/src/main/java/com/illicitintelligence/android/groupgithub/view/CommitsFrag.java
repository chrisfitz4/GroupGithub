package com.illicitintelligence.android.groupgithub.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.model.GithubRepos;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitsFrag extends Fragment {

    private GithubRepos repo;
    private final String TAG = "TAG_X";

    @BindView(R.id.reponame_textview_commitfrag)
    TextView repoName;
    @BindView(R.id.language_textview_commitfrag)
    TextView language;
    @BindView(R.id.owner_textview_commitfrag)
    TextView owner;
    @BindView(R.id.recyclerview_commitfrag)
    RecyclerView commits;

    public CommitsFrag(GithubRepos repo){
        this.repo = repo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.commit_layout,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        Log.d(TAG, "onViewCreated: "+repo.getName());
        repoName.setText(repo.getName());
        language.setText(repo.getLanguage());
        owner.setText(repo.getOwner().getLogin());

    }
}
