package com.weekendinc.baseapp.presenter.iview;

import com.weekendinc.baseapp.model.api.data.Error;

/**
 * Created by Fadhlan on 4/19/17.
 */

public interface IView<T> {
    void startLoading();

    void endLoading();

    void onEmpty(String message);

    void onSuccess(T t);

    void onSuccessWithError(T t, Error error);

    void onSucessMessage(String message);

    void onError(Error error);

    void onError(String errorMessage);

    void onError(Throwable throwable);

    void onErrorConnection(String message);

    void onSessionInvalid();
}