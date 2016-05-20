package com.develop.vic.quiz.database;


import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by vic on 17/05/2016.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {

    public static final String NAME = "quizDB"; // we will add the .db extension

    public static final int VERSION = 1;
}