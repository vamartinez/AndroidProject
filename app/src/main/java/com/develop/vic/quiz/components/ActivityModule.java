package com.develop.vic.quiz.components;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.develop.vic.quiz.models.Quiz;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Victor on 20/5/16.
 */
//@ActivityScope
@Module
public class ActivityModule {
    final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Context activityContext() {
        return mActivity;
    }
}
