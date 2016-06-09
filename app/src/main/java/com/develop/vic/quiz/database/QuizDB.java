package com.develop.vic.quiz.database;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.develop.vic.quiz.App;
import com.develop.vic.quiz.interfaces.QuizValidation;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.provider.ContentUri;
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint;

import com.raizlabs.android.dbflow.structure.provider.BaseSyncableProviderModel;
import com.raizlabs.android.dbflow.structure.provider.ContentUtils;


/**
 * Created by vic on 17/05/2016.
 */

@TableEndpoint(name = QuizDB.NAME, contentProvider = AppDatabase.class)
@ModelContainer
@Table(database = AppDatabase.class, name = QuizDB.NAME)
public class QuizDB extends BaseSyncableProviderModel<QuizDB> {

    public static final String NAME = "QuizDB";

    @ContentUri(path = NAME, type = ContentUri.ContentType.VND_MULTIPLE + NAME)
    public static final Uri CONTENT_URI = ContentUtils.buildUri(AppDatabase.BASE_CONTENT_URI,AppDatabase.AUTHORITY);


    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    long timestamp;
    @Column
    String name;
    @Column
    String description;

    public QuizDB() {
    }

    public QuizDB(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestionCount() {
        return 0;
    }

    public long getId() {
        return id;
    }

    @Override
    public Uri getDeleteUri() {
        return QuizDB.CONTENT_URI;
    }

    @Override
    public Uri getInsertUri() {
        return QuizDB.CONTENT_URI;
    }

    @Override
    public Uri getUpdateUri() {
        return QuizDB.CONTENT_URI;
    }

    @Override
    public Uri getQueryUri() {
        return QuizDB.CONTENT_URI;
    }
}
