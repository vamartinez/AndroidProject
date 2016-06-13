package com.develop.vic.quiz.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuestionDB;
import com.develop.vic.quiz.database.QuestionDB_Table;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.models.Quiz;
import com.develop.vic.quiz.ui.Constant;
import com.develop.vic.quiz.ui.EditQuizActivity;
import com.develop.vic.quiz.ui.QuizDetailActivity;
import com.develop.vic.quiz.ui.QuizListActivity;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 9/6/16.
 */
public class QuizCursorAdapter extends RecyclerView.Adapter<Quiz.ViewHolder> implements View.OnClickListener {

    // Because RecyclerView.Adapter in its current form doesn't natively
    // support cursors, we wrap a CursorAdapter that will do all the job
    // for us.
    CursorAdapter mCursorAdapter;
    private Cursor items;
    Context mContext;
    private Activity context;
    Cursor cursor;
    List<QuizDB> list;
    private RecyclerView.Adapter adapter;


    public QuizCursorAdapter(Context context, List<QuizDB> data) {
        mContext = context;
        list = data;
        adapter = this;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onBindViewHolder(Quiz.ViewHolder holder, int position) {
        QuizDB quizDB = list.get(position);
        holder.mIdView.setText(quizDB.getName());
        holder.mContentView.setText(quizDB.getName());
        holder.counterTV.setText(quizDB.getDescription());
        ArrayList<Object> list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.EDIT_OPTION);
        list.add(2, quizDB.getId());
        holder.editBTN.setTag(list);
        holder.editBTN.setOnClickListener(this);
        list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.DROP);
        list.add(2, quizDB.getId());
        holder.dropBTN.setTag(list);
        holder.dropBTN.setOnClickListener(this);
        list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.EXTRA_OPTION);
        list.add(2, quizDB.getId());
        holder.mView.setTag(list);
        holder.mView.setOnClickListener(this);

    }

    @Override
    public Quiz.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list_content, parent, false);
        return new Quiz.ViewHolder(view);
    }


    @Override
    public void onClick(View v) {
        List<Object> tag = (List<Object>) v.getTag();
        switch (((String) tag.get(1))) {
            case Constant.DROP:
                QuizDB item = list.get((int) tag.get(0));
                item.delete();
                adapter.notifyItemRemoved((int) tag.get(0));
                break;
            case Constant.EDIT_OPTION:
                Intent intent = new Intent(v.getContext(), EditQuizActivity.class);
                intent.putExtra(Constant.QUIZ_ID, (long) tag.get(2));
                v.getContext().startActivity(intent);
                break;
            case Constant.EXTRA_OPTION:
                Intent i = new Intent(v.getContext(), QuizDetailActivity.class);
                i.putExtra(Constant.QUIZ_ID, (long) tag.get(2));
                v.getContext().startActivity(i);
                break;
        }

    }
}