package com.illicitintelligence.android.groupgithub.view;

import android.content.Intent;
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

import com.illicitintelligence.android.groupgithub.BuildConfig;
import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.network.OAuthenticationInstance;

public class LoginFragment extends Fragment {

    Button loginButton;
    EditText usernameEdit;
    WebView webView;

    OAuthenticationInstance oauth;

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

        oauth = new OAuthenticationInstance();

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

        String URL = "https://github.com/login/oauth/authorize" + "?client_id=" + BuildConfig.clientID + "&login=" + userName + "&scope=repo";
        webView.loadUrl(URL);
        webView.setVisibility(View.VISIBLE);
    }
}
