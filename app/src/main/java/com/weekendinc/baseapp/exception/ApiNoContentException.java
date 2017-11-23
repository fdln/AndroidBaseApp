package com.weekendinc.baseapp.exception;

import android.util.Log;

import com.weekendinc.baseapp.helper.WLog;
import com.weekendinc.baseapp.model.api.data.Error;

/**
 * Created by Fadhlan on 4/18/17.
 */

public class ApiNoContentException extends Exception {

    private static final String LOG_TAG = ApiNoContentException.class.getSimpleName();
    private Error error;

    public ApiNoContentException(String message) {
        super(message);

        WLog.e(LOG_TAG, "NO CONTENT EXCEPTION: " + message);
    }

    public ApiNoContentException(Error error) {
        this(error.getMessage());
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}