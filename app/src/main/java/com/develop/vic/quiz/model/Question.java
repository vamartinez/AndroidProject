package com.develop.vic.quiz.model;

import com.develop.vic.quiz.interfaces.QuestionValidation;
import com.develop.vic.quiz.interfaces.QuizValidation;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by vic on 17/05/2016.
 */
@Table(database = AppDatabase.class)
public class Question  extends BaseModel implements QuestionValidation {

    @Override
    public void valid() {

    }
}