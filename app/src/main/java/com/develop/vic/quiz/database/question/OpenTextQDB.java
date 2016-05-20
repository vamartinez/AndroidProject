package com.develop.vic.quiz.database.question;

import com.develop.vic.quiz.database.AppDatabase;
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
public class OpenTextQDB extends BaseModel  {
    @PrimaryKey
    long id;
    @Column
    long timestamp;
    @Column
    String name;
    @Column
    int maxLenght;
    @Column
    long quiz;

}