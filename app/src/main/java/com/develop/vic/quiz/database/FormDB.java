package com.develop.vic.quiz.database;

import com.develop.vic.quiz.interfaces.QuizValidation;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by vic on 17/05/2016.
 */
@ModelContainer
@Table(database = AppDatabase.class)
public class FormDB extends BaseModel  {

    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    long quiz;
    @Column
    String timestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuiz() {
        return quiz;
    }

    public void setQuiz(long quiz) {
        this.quiz = quiz;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
