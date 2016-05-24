package com.develop.vic.quiz.models;


import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.develop.vic.quiz.App;
import com.develop.vic.quiz.R;
import com.develop.vic.quiz.components.AppComponent;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.dummy.DummyContent;


/**
 * Created by vic on 19/05/2016.
 */
public class Quiz {

    private QuizDB quizDB;
   // protected final AppComponent mComponent;

    public Quiz(App app) {
      //  mComponent = app.getAppComponent();
    //    this.quizDB = new QuizDB();
    }



    public void save() {
        quizDB.save();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
