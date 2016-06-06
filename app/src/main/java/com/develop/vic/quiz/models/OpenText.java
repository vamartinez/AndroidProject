package com.develop.vic.quiz.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuestionDB;
import com.develop.vic.quiz.ui.Constant;

import java.util.ArrayList;

/**
 * Created by vic on 18/05/2016.
 */
public class OpenText extends BaseElement {

    private OpenText.ViewHolder holder;
    public static final int CODE = 1;


    public OpenText(QuestionDB questionDB) {
        answerLayout = R.layout.open_text_answer;
        this.questionDB = questionDB;
    }

    public OpenText() {
        answerLayout = R.layout.open_text_answer;
        this.questionDB = new QuestionDB();
    }

    @Override
    public boolean valid() {
        if (!(holder.titleTV.toString().length() > 0) || !(holder.maxLenght.toString().length() > 0))
            return false;
        try {
            Integer.parseInt(holder.maxLenght.getText().toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void extraOption(View v, String text, int position) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.open_text_add_content;
    }

    @Override
    public RecyclerView.ViewHolder getHolder(View itemView) {
        holder = new OpenText.ViewHolder(itemView);
        return holder;
    }

    @Override
    public void bindHolder(RecyclerView.ViewHolder originHolder, final int position) {
        OpenText.ViewHolder holder = (OpenText.ViewHolder) originHolder;
        ArrayList<Object> list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.DROP);
        holder.dropBTN.setTag(list);
        holder.dropBTN.setOnClickListener(listener);
        list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.EXTRA_OPTION);
        if (questionDB != null) {
            holder.titleTV.setText(questionDB.getName());
            holder.maxLenght.setText(String.valueOf(questionDB.getMaxLenght()));
        }
        holder.titleTV.setOnFocusChangeListener(changeSaveListener(position));
        holder.maxLenght.setOnFocusChangeListener(changeSaveListener(position));
    }


    @Override
    public void save(int order) {
        if (questionDB == null) questionDB = new QuestionDB();
        questionDB.setMaxLenght(Integer.parseInt(holder.maxLenght.getText().toString()));
        questionDB.setName(holder.titleTV.getText().toString());
        questionDB.setType(OpenText.CODE);
        questionDB.setOrder(order);
    }

    @Override
    protected void loadAnswerData(View v) {
        ((TextView)v.findViewById(R.id.questionTV)).setText(questionDB.getName());
        ((EditText)v.findViewById(R.id.answerET)).setMaxEms(questionDB.getMaxLenght());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText titleTV;
        public final EditText maxLenght;
        public final Button dropBTN;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleTV = (EditText) view.findViewById(R.id.titleTV);
            maxLenght = (EditText) view.findViewById(R.id.maxLenght);
            dropBTN = (Button) view.findViewById(R.id.dropBTN);
            dropBTN.setTag(getAdapterPosition(), Constant.DROP);
        }
    }

}


