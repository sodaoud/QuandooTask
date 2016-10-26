package com.quandoo.sodaoud.app.di.modules;

import com.quandoo.sodaoud.app.QuandooApp;

/**
 * Created by sofiane on 10/25/16.
 */

//@Module
public class ApplicationModule {
    private QuandooApp application;

    public ApplicationModule(QuandooApp application) {
        this.application = application;
    }

}
