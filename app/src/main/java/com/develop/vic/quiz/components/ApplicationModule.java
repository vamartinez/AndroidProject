/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.develop.vic.quiz.components;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.develop.vic.quiz.App;
import com.develop.vic.quiz.controler.QuizController;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.models.Quiz;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

       @Provides
       @Singleton
       public Context appContext() {
           return mApp;
       }

         @Provides
         @Singleton
         public Quiz quiz() {
             return new Quiz(mApp);
         }

    @Provides
    @Singleton
    public QuizController quizController() {
        return new QuizController(mApp.getAppComponent());
    }

}