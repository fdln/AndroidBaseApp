package com.weekendinc.baseapp;

/**
 * Created by Fadhlan on 2/28/17.
 */

public class Application extends android.app.Application {
    private static Application instance;

    public Application() {
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }
}
