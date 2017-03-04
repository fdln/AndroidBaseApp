package com.weekendinc.baseapp.net;

import com.weekendinc.baseapp.model.entity.GithubUser;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Fadhlan on 3/2/17.
 */

public interface GithubService extends Service {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/users/{login}")
    Observable<GithubUser> getUser(@Path("login") String username);
}
