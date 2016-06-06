package com.develop.vic.quiz.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuestionDB;
import com.develop.vic.quiz.interfaces.QuestionValidation;

/**
 * Created by vic on 19/05/2016.
 */
public abstract class BaseElement implements QuestionValidation {
    public static final int CODE = 0;
    protected QuestionDB questionDB;
    protected View.OnClickListener listener;
    protected int answerLayout = -1;

    public abstract void extraOption(View view, String text, int position);

    public abstract int getLayoutId();

    public abstract RecyclerView.ViewHolder getHolder(View itemView);

    public abstract void bindHolder(RecyclerView.ViewHolder holder, int position);

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public abstract void save(int order);

    public void persist(long quizID) {
        questionDB.setQuiz(quizID);
        questionDB.save();
    }

    public View getAnswerView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(answerLayout, null);
        loadAnswerData(v);
        return v;
    }

    protected abstract void loadAnswerData(View v);


    protected View.OnFocusChangeListener changeSaveListener(final int position) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                save(position);
            }
        };
    }
}
