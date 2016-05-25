package com.develop.vic.quiz.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.models.Quiz;
import com.raizlabs.android.dbflow.list.FlowCursorList;

/**
 * Created by vic on 19/05/2016.
 */
public class QuizAdapter extends RecyclerView.Adapter<Quiz.ViewHolder> {

    private FlowCursorList<QuizDB> mFlowCursorList;

    public QuizAdapter() {
        if (mFlowCursorList == null)
            mFlowCursorList = new FlowCursorList<QuizDB>(false,QuizDB.class);
    }

    @Override
    public Quiz.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list_content, parent, false);
        return new Quiz.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Quiz.ViewHolder holder, int position) {

        Log.e(this.toString(),"esto es"+mFlowCursorList.getItem(position).getName());
    }

    @Override
    public int getItemCount() {
        Log.e(this.toString(),"size es"+ mFlowCursorList.getCount());
        return mFlowCursorList.getCount();
    }
}
