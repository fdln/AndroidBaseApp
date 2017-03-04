package com.weekendinc.baseapp.net;

import com.weekendinc.baseapp.model.entity.GithubUser;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Fadhlan on 2/16/17.
 */
public class ApiManager {
    private GithubService githubService;
    private static ApiManager instance = new ApiManager();

    public static ApiManager getInstance() {
        return instance;
    }

    private ApiManager() {
        ApiConnection apiConnection = ApiConnection.getInstance();
        this.githubService = apiConnection.getGithubService();
    }

    private final Observable.Transformer<Object, Object> schedulersTransformer =
            new Observable.Transformer<Object, Object>() {
                @Override public Observable<Object> call(Observable<Object> o) {
                    return o.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                }
            };

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

    private <T> void execute(Observable<? extends T> observable, Subscriber<T> subscriber) {
        observable.compose(this.applySchedulers())
                .flatMap(new Func1<Object, Observable<T>>() {
                    @Override public Observable<T> call(Object o) {
                        return Observable.just(o);
                    }
                }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override public Observable<?> call(
                    Observable<? extends Throwable> observable) {
                return null;
            }
        }).subscribe(subscriber);
    }

    public void getGithubUser(String username, Subscriber<GithubUser> subscriber) {
        execute(githubService.getUser(username), subscriber);
    }
}
