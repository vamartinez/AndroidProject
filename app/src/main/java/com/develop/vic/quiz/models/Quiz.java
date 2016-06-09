package com.develop.vic.quiz.models;


import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.develop.vic.quiz.App;
import com.develop.vic.quiz.R;
import com.develop.vic.quiz.components.AppComponent;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.database.QuizDB_Table;
import com.develop.vic.quiz.dummy.DummyContent;
import com.raizlabs.android.dbflow.sql.language.SQLite;


/**
 * Created by vic on 19/05/2016.
 */
public class Quiz {

    private QuizDB quizDB;
    protected final AppComponent mComponent;

    public Quiz(App app) {
        mComponent = app.getAppComponent();
        app.getAppComponent().inject(this);
        this.quizDB = new QuizDB();
    }


    public void save() {
        quizDB.save();
    }

    public void setData(String name, String description) {
        this.quizDB.setDescription(description);
        this.quizDB.setName(name);
    }

    public long getId() {
        return quizDB.getId();
    }

    public void setID(long id) {
        QuizDB quiz = SQLite.select()
                .from(QuizDB.class)
                .where(QuizDB_Table.id.eq(id)).querySingle();
        if (quiz != null) this.quizDB = quiz;
    }

    public String getTitle() {
        return quizDB.getName();
    }

    public String getDescription() {
        return quizDB.getDescription();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView counterTV;
        public final Button editBTN;
        public final Button dropBTN;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.titleTV);
            mContentView = (TextView) view.findViewById(R.id.descriptionTV);
            counterTV = (TextView) view.findViewById(R.id.counterTV);
            editBTN = (Button) view.findViewById(R.id.editBTN);
            dropBTN = (Button) view.findViewById(R.id.dropBTN);


        }

    }
}
