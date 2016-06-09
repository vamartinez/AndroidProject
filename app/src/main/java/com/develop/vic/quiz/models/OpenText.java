package com.develop.vic.quiz.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.AnswerDB;
import com.develop.vic.quiz.database.AnswerDB_Table;
import com.develop.vic.quiz.database.FormDB;
import com.develop.vic.quiz.database.FormDB_Table;
import com.develop.vic.quiz.database.QuestionDB;
import com.develop.vic.quiz.database.QuestionDB_Table;
import com.develop.vic.quiz.ui.Constant;
import com.develop.vic.quiz.ui.adapter.ResultAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 18/05/2016.
 */
public class OpenText extends BaseElement {

    private static final int DEFAULT_MAX_LENGTH = 50;
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
    public void saveAnswer(long formId) {
        if (answerDB == null) answerDB = new AnswerDB();
        answerDB.setForm(formId);
        answerDB.setQuestion(questionDB.getId());
        answerDB.setResponse(((EditText) answerView.findViewById(R.id.answerET)).getText().toString());
        answerDB.save();
    }


    @Override
    public void bindResponseHolder(RecyclerView.ViewHolder originHolder, int position, long quizId) {
        BaseElement.ViewHolder holder = (BaseElement.ViewHolder) originHolder;
        holder.titleTV.setText(questionDB.getName());
        List<AnswerDB> answerDBList = SQLite.select()
                .from(AnswerDB.class)
                .where(AnswerDB_Table.question.eq(questionDB.getId()))
                .queryList();
        for (AnswerDB answer : answerDBList) {
            TextView textView = new TextView(((BaseElement.ViewHolder) originHolder).mView.getContext());
            textView.setText(answer.getResponse());
            holder.responseContainerLL.addView(textView);
        }
    }

    @Override
    protected void loadAnswerData(View v) {
        ((TextView) v.findViewById(R.id.questionTV)).setText(questionDB.getName());
        InputFilter[] fArray = new InputFilter[1];
        int maxLenght = questionDB.getMaxLenght();
        if (maxLenght == 0) maxLenght = DEFAULT_MAX_LENGTH;
        fArray[0] = new InputFilter.LengthFilter(maxLenght);
        ((EditText) v.findViewById(R.id.answerET)).setFilters(fArray);
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


