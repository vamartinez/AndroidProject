package com.develop.vic.quiz.database;


import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider;

/**
 * Created by vic on 17/05/2016.
 */
@ContentProvider(authority = AppDatabase.AUTHORITY,
        database = AppDatabase.class,
        baseContentUri = AppDatabase.BASE_CONTENT_URI)
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {
    public static final String AUTHORITY = "com.develop.vic.quiz.database";
    public static final String BASE_CONTENT_URI = "content://";
    public static final String NAME = "quizDBase";
    public static final int VERSION = 1;
}