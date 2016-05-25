package com.develop.vic.quiz.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.models.BaseElement;
import com.develop.vic.quiz.models.OpenText;
import com.develop.vic.quiz.models.Quiz;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 19/05/2016.
 */
public class QuestionAdapter extends RecyclerView.Adapter<BaseElement.questionHolder> {

    private List<BaseElement> questionList;

    public QuestionAdapter() {
        questionList = new ArrayList<BaseElement>();
    }

    public void add(BaseElement element) {
        questionList.add(element);
        this.notifyItemInserted(questionList.size() - 1);
    }

    @Override
    public BaseElement.questionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list_content, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(BaseElement.questionHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return questionList.size();

    }
}
