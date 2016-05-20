package com.develop.vic.quiz.database.answer;

import com.develop.vic.quiz.database.AppDatabase;
import com.develop.vic.quiz.interfaces.QuestionValidation;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.security.Timestamp;

/**
 * Created by vic on 18/05/2016.
 */
@ModelContainer
@Table(database = AppDatabase.class)
public class OpenTextADB extends BaseModel implements QuestionValidation {

    @PrimaryKey
    long id;
    @Column
    long timestamp;
    @Column
    String response;
    @Column
    long question;


    @Override
    public void valid() {

    }
}

