package com.weekendinc.baseapp.presenter;

import android.util.Log;

import com.weekendinc.baseapp.model.entity.GithubUser;
import com.weekendinc.baseapp.net.ApiManager;
import com.weekendinc.baseapp.view.DataView;

import java.util.Locale;

import rx.Subscriber;

import static android.content.ContentValues.TAG;

/**
 * Created by Fadhlan on 3/2/17.
 */

public class GithubPresenter implements Presenter {

    private static final String LOG_TAG = GithubPresenter.class.getSimpleName();

    private DataView dataView;

    @Override public void setDataView(DataView dataView) {
        this.dataView = dataView;
    }

    public void loadUser(String name) {
        apiManager.getGithubUser(name, new Subscriber<GithubUser>() {
            @Override public void onCompleted() {
                Log.i(LOG_TAG, "onCompleted: ");

            }

            @Override public void onError(Throwable e) {

            }

            @Override public void onNext(GithubUser githubUser) {
                Log.e(LOG_TAG,
                      String.format(Locale.getDefault(), "onNext: %s", githubUser.toString()));
            }
        });
    }
}
