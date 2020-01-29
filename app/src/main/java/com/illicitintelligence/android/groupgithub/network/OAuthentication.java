package com.illicitintelligence.android.groupgithub.network;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OAuthentication {

    @Headers(
            {"Accept: application/json"}
    )
    @POST("/login/oauth/access_token")
    public Observable<UserAccessToken> getToken(
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("code") String code
    );

}
