package com.develop.vic.quiz.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuestionDB;
import com.develop.vic.quiz.database.QuestionDB_Table;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.models.Quiz;
import com.develop.vic.quiz.ui.Constant;
import com.develop.vic.quiz.ui.EditQuizActivity;
import com.develop.vic.quiz.ui.FormActivity;
import com.develop.vic.quiz.ui.QuizDetailActivity;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 19/05/2016.
 */
public class QuizAdapter extends RecyclerView.Adapter<Quiz.ViewHolder> implements View.OnClickListener {

    private FlowCursorList<QuizDB> mFlowCursorList;

    public QuizAdapter(Context context) {
        if (mFlowCursorList == null)
            mFlowCursorList = new FlowCursorList<QuizDB>(false, QuizDB.class);
        FlowContentObserver observer = new FlowContentObserver();
        observer.registerForContentChanges(context, QuizDB.class);
        observer.addModelChangeListener(new FlowContentObserver.OnModelStateChangedListener() {
            @Override
            public void onModelStateChanged(@Nullable Class<? extends Model> table, BaseModel.Action action, @NonNull SQLCondition[] primaryKeyValues) {
               notifyDataSetChanged();
            }
        });
        FlowContentObserver observerQ = new FlowContentObserver();
        observerQ.registerForContentChanges(context, QuestionDB.class);
        observerQ.addModelChangeListener(new FlowContentObserver.OnModelStateChangedListener() {
            @Override
            public void onModelStateChanged(@Nullable Class<? extends Model> table, BaseModel.Action action, @NonNull SQLCondition[] primaryKeyValues) {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public Quiz.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list_content, parent, false);
        return new Quiz.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Quiz.ViewHolder holder, int position) {
        QuizDB item = mFlowCursorList.getItem(position);
        holder.mIdView.setText(item.getName());
        holder.mContentView.setText(item.getDescription());
        holder.counterTV.setText(String.valueOf(item.getQuestionCount()));
        ArrayList<Object> list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.EDIT_OPTION);
        holder.editBTN.setTag(list);
        holder.editBTN.setOnClickListener(this);
        list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.DROP);
        holder.dropBTN.setTag(list);
        holder.dropBTN.setOnClickListener(this);
        list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.EXTRA_OPTION);
        holder.mView.setTag(list);
        holder.mView.setOnClickListener(this);
        long count = SQLite.select()
                .from(QuestionDB.class)
                .where(QuestionDB_Table.quiz.eq(item.getId()))
                .count();
        holder.counterTV.setText(item.getId()+" "+count+" questions");

    }

    @Override
    public int getItemCount() {
        return mFlowCursorList.getCount();
    }

    @Override
    public void onClick(View v) {
        List<Object> tag = (List<Object>) v.getTag();
        switch (((String) tag.get(1))) {
            case Constant.DROP:
                mFlowCursorList.getItem((int) tag.get(0)).delete();
                this.notifyItemRemoved((int) tag.get(0));
                break;
            case Constant.EDIT_OPTION:
                Intent intent = new Intent(v.getContext(), EditQuizActivity.class);
                intent.putExtra(Constant.QUIZ_ID, (int) tag.get(0));
                v.getContext().startActivity(intent);
                break;
            case Constant.EXTRA_OPTION:
                Intent i = new Intent(v.getContext(), QuizDetailActivity.class);
                i.putExtra(Constant.QUIZ_ID, (int) tag.get(0));
                v.getContext().startActivity(i);
                break;
        }

    }
}
