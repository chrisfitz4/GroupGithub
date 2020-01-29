package com.illicitintelligence.android.groupgithub.network;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.illicitintelligence.android.groupgithub.BuildConfig;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OAuthenticationInstance {

    private final String OAuth_URL = "https://github.com";

    private OAuthentication oauth;

    public OAuthenticationInstance () {
        oauth = createOAuthInstance(createRetrofitInstance());
    }

    private Retrofit createRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(OAuth_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OAuthentication createOAuthInstance(Retrofit retrofit) {
        return retrofit.create(OAuthentication.class);
    }

    public Observable<UserAccessToken> getToken(String OAuth_code) {
        return oauth.getToken(BuildConfig.clientID, BuildConfig.clientSecret, OAuth_code);
    }

    public Intent getAuthorization(String login) {
        String URL = OAuth_URL + "/login/oauth/authorize" + "?client_id=" + BuildConfig.clientID + "&login=" + login + "&scope=repo";
        Intent oauthIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(URL)
        );

        return oauthIntent;
    }
}
