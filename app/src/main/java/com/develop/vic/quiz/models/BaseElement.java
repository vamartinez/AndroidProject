package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.develop.vic.quiz.interfaces.QuestionValidation;

/**
 * Created by vic on 19/05/2016.
 */
public abstract class BaseElement implements QuestionValidation {

    public abstract void extraOption(View view);

    public abstract int getLayoutId();

    public abstract RecyclerView.ViewHolder getHolder(View itemView, View.OnClickListener listener);


}
