package com.develop.vic.quiz.model.question;

import com.develop.vic.quiz.interfaces.QuestionValidation;
import com.develop.vic.quiz.interfaces.QuizValidation;
import com.develop.vic.quiz.model.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.security.Timestamp;

/**
 * Created by vic on 17/05/2016.
 */
@Table(database = AppDatabase.class)
public class OpenTextQ extends BaseModel  {
    @PrimaryKey
    long id;
    @Column
    Timestamp timestamp;
    @Column
    String name;
    @Column
    int maxLenght;
    @Column
    long quiz;

}