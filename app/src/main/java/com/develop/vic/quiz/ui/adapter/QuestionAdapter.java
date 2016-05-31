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
public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<BaseElement> questionList;

    public QuestionAdapter(ArrayList<BaseElement> lista) {
        questionList = lista;
    }

    public void add(BaseElement element) {
        questionList.add(element);
     //   this.notifyItemInserted(questionList.size() - 1);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //  Log.e("create",questionList.get(viewType).getLayoutId()+"aa"+viewType);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list_content, parent, false);
        return new Quiz.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {

        Log.e(this.toString(),"Bind!!");
    }

    @Override
    public int getItemCount() {
        Log.e(this.toString(),"sdize"+questionList.size());
        return questionList.size();

    }
}
