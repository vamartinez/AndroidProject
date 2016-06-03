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
import com.develop.vic.quiz.ui.Constant;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 19/05/2016.
 */
public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private ArrayList<BaseElement> questionList;

    public QuestionAdapter() {
        questionList = new ArrayList<>();
    }

    public void add(BaseElement element) {
        questionList.add(element);
        this.notifyItemInserted(questionList.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(questionList.get(viewType).getLayoutId(), parent, false);
        return questionList.get(0).getHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e(this.toString(), "Bind!!");
    }

    @Override
    public int getItemCount() {
        return questionList.size();

    }

    // onClick Listener for view
    @Override
    public void onClick(View v) {
        List<Object> tag = (List<Object>) v.getTag();
        Log.e(this.toString(),"tags>"+tag.toString());
        switch (((String)tag.get(1))) {
            case Constant.DROP:
                questionList.remove((int) tag.get(0));
                this.notifyItemRemoved((int) tag.get(0));
                break;
            case Constant.EXTRA_OPTION:
           //     questionList.get((int) tag.get(0)).extraOption(v);
                break;

        }
    }

}
