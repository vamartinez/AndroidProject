package com.develop.vic.quiz.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.develop.vic.quiz.App;
import com.develop.vic.quiz.components.ActivityComponent;
import com.develop.vic.quiz.components.DaggerActivityComponent;

/**
 * Created by Victor on 20/5/16.
 */
public class BaseActivity extends AppCompatActivity {
    private ActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder()
                .appComponent(getApp().getAppComponent()).build();
    }

    protected App getApp() {
        return (App) getApplicationContext();
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }


}
