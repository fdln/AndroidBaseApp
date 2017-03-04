package com.weekendinc.baseapp.net;

import com.weekendinc.baseapp.model.entity.GithubUser;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Fadhlan on 2/16/17.
 */
public class ApiManager {
    private GithubService githubService;
    private static ApiManager instance;

    public static ApiManager getInstance() {
        return instance == null ? (instance = new ApiManager()) : instance;
    }

    private ApiManager() {
        ApiConnection apiConnection = ApiConnection.getInstance();
        this.githubService = apiConnection.getGithubService();
    }

    private Observable.Transformer ioToMainThreadSchedulerTransformer
            = createIOMainThreadScheduler();


    private <T> Observable.Transformer<T, T> createIOMainThreadScheduler() {
        return new Observable.Transformer<T, T>() {
            @Override public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applyIOMainThreadSchedulers() {
        return ioToMainThreadSchedulerTransformer;
    }

    @SuppressWarnings("unchecked")
    private <T> void execute(Observable<? extends T> o, Subscriber<T> s) {
        o.compose((Observable.Transformer<T, T>) applyIOMainThreadSchedulers())
                .flatMap(new Func1<T, Observable<T>>() {
                    @Override public Observable<T> call(T t) {
                        return Observable.just(t);
                    }
                })
                .subscribe(s);
    }

    public void getGithubUser(String username, Subscriber<GithubUser> subscriber) {
        execute(githubService.getUser(username), subscriber);
    }
}
