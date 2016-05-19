package com.develop.vic.quiz;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.develop.vic.quiz.components.AppComponent;
import com.develop.vic.quiz.components.ApplicationModule;
import com.develop.vic.quiz.components.DaggerAppComponent;


/**
 * Created by vic on 18/05/2016.
 */

public class App extends Application {
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        //FlowManager.init(this);

        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}
