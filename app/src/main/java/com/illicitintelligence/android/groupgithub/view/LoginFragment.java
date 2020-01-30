package com.illicitintelligence.android.groupgithub.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.OAuthProvider;
import com.illicitintelligence.android.groupgithub.BuildConfig;
import com.illicitintelligence.android.groupgithub.R;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {

    Button loginButton;
    EditText usernameEdit;
    WebView webView;

    OAuthProvider.Builder provider = OAuthProvider.newBuilder("github.com");

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
        webView = view.findViewById(R.id.login_github_webview);
        webView.setVisibility(View.GONE);

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

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("GroupGithub", Context.MODE_PRIVATE);

        Task<AuthResult> task = firebaseAuth.getPendingAuthResult();
        if(task != null) {
            task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    String userName = authResult.getAdditionalUserInfo().getUsername();
                    OAuthCredential credential = (OAuthCredential)authResult.getCredential();
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
                            OAuthCredential credential = (OAuthCredential)authResult.getCredential();
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
