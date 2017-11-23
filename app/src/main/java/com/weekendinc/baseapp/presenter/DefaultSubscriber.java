package com.weekendinc.baseapp.presenter;

import com.weekendinc.baseapp.exception.ApiNoContentException;
import com.weekendinc.baseapp.exception.SessionInvalidException;
import com.weekendinc.baseapp.helper.WLog;
import com.weekendinc.baseapp.model.api.BaseResponse;
import com.weekendinc.baseapp.presenter.iview.DefaultDataView;
import com.weekendinc.baseapp.presenter.iview.IView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import rx.Subscriber;

/**
 * Created by Fadhlan on 3/3/17.
 */

public class DefaultSubscriber<
        T extends BaseResponse<D>,
        V extends DefaultDataView<D> & IView<D>,
        D>
        extends Subscriber<T> {
    private static final String LOG_TAG = DefaultSubscriber.class.getSimpleName();
    protected V view;

    public DefaultSubscriber() {
        super();
    }

    public DefaultSubscriber(V view) {
        this.view = view;
        if (view == null) throw new IllegalArgumentException("ERR: null view");
    }

    @Override
    public void onStart() {
        super.onStart();
        view.startLoading();
    }

    @Override
    public void onCompleted() {
        view.endLoading();
        WLog.e(LOG_TAG, "onCompleted() called");
    }

    @Override
    public void onError(Throwable e) {
        WLog.e(LOG_TAG, "onError Throw class: " + e.getClass());
        WLog.e(LOG_TAG, " :----->: onError(): ", e);
        if (e instanceof ApiNoContentException) {
            view.onEmpty(((ApiNoContentException) e).getError().getMessage());
            view.endLoading();
            return;
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException
                || e instanceof SSLException || e instanceof ConnectException) {
            view.onErrorConnection(e.getMessage());
            return;
        } else if (e instanceof SessionInvalidException)
            view.onSessionInvalid();

        view.onError(e.getMessage());
        view.onError(e);
    }

    @Override
    public void onNext(T t) {
        WLog.d(LOG_TAG, "onNext() called with: t = [" + t + "]");
        if (t.getError() != null)
            if (t.getError().getCode() > 200 && t.getError().getCode() <= 299) {
                view.onSuccessWithError(t.getData(), t.getError());
            } else
                view.onError(t.getError());
        else view.onSuccess(t.getData());
    }
}