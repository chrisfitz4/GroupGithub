package com.illicitintelligence.android.groupgithub.view;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.OAuthProvider;
import com.illicitintelligence.android.groupgithub.BuildConfig;
import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.util.Constants;
import com.illicitintelligence.android.groupgithub.viewmodel.GithubViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {

    private Button loginButton;
    private EditText usernameEdit;

    private TextView usernameText;
    private TextView tokenText;
    private CheckBox checkBox;

    private OAuthProvider.Builder provider = OAuthProvider.newBuilder("github.com");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = view.findViewById(R.id.login_Github_button);
        usernameEdit = view.findViewById(R.id.login_username_editText);
        usernameText = view.findViewById(R.id.login_username_textView);
        tokenText = view.findViewById(R.id.login_usertoken_textView);
        checkBox = view.findViewById(R.id.checkbox);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });
    }

    public void onLogin() {

        String userName = usernameEdit.getText().toString().trim();

        if(userName.isEmpty()) {
            Log.d("TAG_X", "onLogin: UserName is Empty.");
            Toast.makeText(getContext(), "Username cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //Intent github = oauth.getAuthorization(userName);
        //startActivity(github);



        provider.addCustomParameter("login", userName);
        List<String> scopes = new ArrayList<>();
        scopes.add("repo");
        provider.setScopes(scopes);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.getReposForUser(userName);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.DUMMY_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        String alreadyIn=sharedPreferences.getString(Constants.DUMMY_SHAREDPREFERENCES_KEY,"");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(alreadyIn.equals("")) {
            editor.putString(Constants.DUMMY_SHAREDPREFERENCES_KEY,userName+"."+getContext().getColor(R.color.dandelion));
        }else if(alreadyIn.contains(userName)){
            Toast.makeText(this.getContext(),"User already in the system",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.getContext(),"User has been added", Toast.LENGTH_SHORT).show();
            editor.putString(Constants.DUMMY_SHAREDPREFERENCES_KEY,alreadyIn+","+userName+"."+getContext().getColor(R.color.dandelion));
            Intent intent = new Intent();
            intent.setAction("reread_shared_preferences");
            getContext().sendBroadcast(intent);
        }
        editor.apply();
        editor.commit();

        if(!checkBox.isChecked()){
            return;
        }

        firebaseAuth.signOut();

        Task<AuthResult> task = firebaseAuth.getPendingAuthResult();
        if(task != null) {
            task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    String userName = authResult.getAdditionalUserInfo().getUsername();
                    usernameText.setText(userName);
                    OAuthCredential credential = (OAuthCredential)authResult.getCredential();
                    tokenText.setText(credential.getIdToken() + ": " + credential.getAccessToken());
                    Toast.makeText(getContext(), credential.getAccessToken(), Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            //add the actual listener
            firebaseAuth.startActivityForSignInWithProvider(getActivity(), provider.build())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            String userName = authResult.getAdditionalUserInfo().getUsername();
                            usernameText.setText(userName);
                            OAuthCredential credential = (OAuthCredential)authResult.getCredential();
                            tokenText.setText(credential.getIdToken() + ": " + credential.getAccessToken());
                            Toast.makeText(getContext(), credential.getAccessToken(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Failed to login", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
