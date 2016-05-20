package com.develop.vic.quiz.database;

import android.database.sqlite.SQLiteDatabase;

import com.develop.vic.quiz.App;
import com.develop.vic.quiz.interfaces.QuizValidation;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.security.Timestamp;


/**
 * Created by vic on 17/05/2016.
 */
@ModelContainer
@Table(database = AppDatabase.class)
public class QuizDB extends BaseModel implements QuizValidation {
    @PrimaryKey
    long id;
    @Column
    long timestamp;
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
