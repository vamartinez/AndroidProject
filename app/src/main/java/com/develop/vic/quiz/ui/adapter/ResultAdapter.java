package com.develop.vic.quiz.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.models.BaseElement;
import com.develop.vic.quiz.ui.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 19/05/2016.
 */
public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private ArrayList<BaseElement> questionList;

    public ResultAdapter() {
        Log.e(this.toString(),"Holder adapter created");
        questionList = new ArrayList<>();
    }

    public void addAll(ArrayList<BaseElement> list) {
        questionList.addAll(list);
    }

    public void add(BaseElement element) {
        questionList.add(element);
        this.notifyItemInserted(questionList.size() - 1);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_row, parent, false);
        return questionList.get(viewType).getResponseHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e(this.toString(),"Holder bind response");
        questionList.get(position).bindResponseHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        Log.e(this.toString(),"Holder list size>>"+questionList.size());
        return questionList.size();
    }


    public ArrayList<BaseElement> getData() {
        return this.questionList;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        questionList.get(holder.getAdapterPosition()).save(holder.getAdapterPosition());
        super.onViewRecycled(holder);
    }

}
