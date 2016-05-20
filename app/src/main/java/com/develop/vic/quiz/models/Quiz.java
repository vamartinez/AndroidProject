package com.develop.vic.quiz.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.dummy.DummyContent;
import com.develop.vic.quiz.ui.QuizDetailActivity;
import com.develop.vic.quiz.ui.QuizDetailFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by vic on 19/05/2016.
 */
public class Quiz {
    private final QuizDB quizDB;
    @Inject
    Context mAppContext;

    public Quiz(QuizDB quizDB) {
        this.quizDB = quizDB;
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
