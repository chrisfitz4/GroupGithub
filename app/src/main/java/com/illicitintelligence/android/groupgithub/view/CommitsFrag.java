package com.illicitintelligence.android.groupgithub.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.adapter.CommitAdapter;
import com.illicitintelligence.android.groupgithub.model.GithubRepos;
import com.illicitintelligence.android.groupgithub.model.commits.CommitResult;
import com.illicitintelligence.android.groupgithub.viewmodel.GithubViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class CommitsFrag extends Fragment {

    private GithubRepos repo;
    private final String TAG = "TAG_X";
    private GithubViewModel viewModel;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.reponame_textview_commitfrag)
    TextView repoName;
    @BindView(R.id.language_textview_commitfrag)
    TextView language;
    @BindView(R.id.owner_textview_commitfrag)
    TextView owner;
    @BindView(R.id.recyclerview_commitfrag)
    RecyclerView commits;
    @BindView(R.id.icon_commitfrag)
    ImageView githubLogo;

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
        viewModel = ViewModelProviders.of(this).get(GithubViewModel.class);
        ButterKnife.bind(this,view);
        Log.d(TAG, "onViewCreated: "+repo.getName()+","+repo.getOwner().getLogin());
        repoName.setText(repo.getName());
        language.setText(repo.getLanguage());
        owner.setText(repo.getOwner().getLogin());
        final CommitAdapter adapter = new CommitAdapter(new ArrayList<CommitResult>());
        commits.setAdapter(adapter);
        commits.setLayoutManager(new LinearLayoutManager(this.getContext()));

        compositeDisposable.add(viewModel.getCommits(repo.getOwner().getLogin(),repo.getName())
                .subscribeWith(new DisposableObserver<List<CommitResult>>() {
                    @Override
                    public void onNext(List<CommitResult> commitResults) {
                        adapter.setCommits(commitResults);
                        adapter.notifyDataSetChanged();
                        if(adapter.getItemCount()>2){
                            githubLogo.setVisibility(View.GONE);
                        }else{
                            githubLogo.setVisibility(View.VISIBLE);
                        }
                        Log.d(TAG, "onNext: "+adapter.getItemCount());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: completed");
                    }
                }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
