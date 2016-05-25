package com.develop.vic.quiz.controler;

import android.content.Context;

import com.develop.vic.quiz.components.AppComponent;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.models.Quiz;


import javax.inject.Inject;

/**
 * Created by vic on 23/05/2016.
 */
public class QuizController {
    @Inject
    Context mAppContext;
    @Inject
    transient Quiz mQuiz;

    public QuizController(AppComponent appComponent)
    {
        appComponent.inject(this);
    }

    public void createQuiz(String name, String description ){
        mQuiz.setData(name,description);
        mQuiz.save();
       // Quiz q = new Quiz(name,description);
    }
}
