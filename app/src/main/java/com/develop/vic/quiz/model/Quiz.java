package com.develop.vic.quiz.model;

import com.develop.vic.quiz.interfaces.QuizValidation;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.security.Timestamp;


/**
 * Created by vic on 17/05/2016.
 */
@Table(database = AppDatabase.class)
public class Quiz  extends BaseModel implements QuizValidation {

    @PrimaryKey
    long id;
    @Column
    Timestamp timestamp;
    @Column
    String name;
    @Column
    boolean mPending;
    @Column
    String mClientId;
    @Column
    long mUserId;


    @Override
    public void validate() {

    }
}
