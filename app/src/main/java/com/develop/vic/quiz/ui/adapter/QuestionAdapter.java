package com.develop.vic.quiz.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.vic.quiz.models.BaseElement;
import com.develop.vic.quiz.ui.Constant;

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

    public void addAll(ArrayList<BaseElement> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setListener(this);
        }
        questionList.addAll(list);

    }

    public void add(BaseElement element) {
        element.setListener(this);
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
        return questionList.get(viewType).getHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        questionList.get(position).bindHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }


    @Override
    public void onClick(View v) {

        List<Object> tag = (List<Object>) v.getTag();
        switch (((String) tag.get(1))) {
            case Constant.DROP:
                questionList.remove((int) tag.get(0));
                this.notifyItemRemoved((int) tag.get(0));
                break;
            case Constant.EXTRA_OPTION:
                questionList.get((int) tag.get(0)).extraOption(v, "",(int) tag.get(0));
                break;
        }
    }

    public ArrayList<BaseElement> getData() {
        return this.questionList;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        Log.e(this.toString(),holder.getAdapterPosition()+"pos rec");
        questionList.get(holder.getAdapterPosition()).save(holder.getAdapterPosition());
        super.onViewRecycled(holder);
    }

}
