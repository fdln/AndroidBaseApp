package com.weekendinc.baseapp.exception;

import com.weekendinc.baseapp.helper.WLog;
import com.weekendinc.baseapp.model.api.data.Error;

/**
 * Created by Fadhlan on 4/18/17.
 */

public class SessionInvalidException extends Exception {

    private static final String LOG_TAG = SessionInvalidException.class.getSimpleName();
    private Error error;

    public SessionInvalidException(String message) {
        super(message);

        WLog.e(LOG_TAG, "SESSION INVALID EXCEPTION: " + message);
    }

    public SessionInvalidException(Error error) {
        this(error.getMessage());
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}