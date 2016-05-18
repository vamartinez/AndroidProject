package com.develop.vic.quiz.model.answer;

import com.develop.vic.quiz.interfaces.QuestionValidation;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.security.Timestamp;

/**
 * Created by vic on 18/05/2016.
 */
public class OpenTextA extends BaseModel implements QuestionValidation {

    @PrimaryKey
    long id;
    @Column
    Timestamp timestamp;
    @Column
    String response;
    @Column
    long question;


    @Override
    public void valid() {

    }
}

