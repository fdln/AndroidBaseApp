package com.weekendinc.baseapp.presenter.iview;

import com.weekendinc.baseapp.model.api.data.Error;
import com.weekendinc.baseapp.presenter.iview.IView;


/**
 * Created by Fadhlan on 6/15/17.
 */

public abstract class DefaultDataView<T> implements IView<T> {
    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void onEmpty(String message) {

    }

    @Override
    public void onSuccess(T t) {
//        if (t instanceof DataItem) {
//            ((DataItem) t).toBuilder().items(
//                    JsonUtility.<V>convert(((DataItem) t).items())).build();
//        }
    }

    @Override
    public void onSuccessWithError(T t, Error error) {

    }

    @Override
    public void onSucessMessage(String message) {

    }

    @Override
    public void onError(Error error) {

    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onErrorConnection(String message) {

    }

    @Override public void onSessionInvalid() {

    }
}