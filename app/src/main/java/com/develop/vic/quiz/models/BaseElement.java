package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.develop.vic.quiz.interfaces.QuestionValidation;

/**
 * Created by vic on 19/05/2016.
 */
public abstract class BaseElement implements QuestionValidation {



    public abstract class questionHolder extends RecyclerView.ViewHolder {
        public questionHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract int getLayoutId();
}
