package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuestionDB;
import com.develop.vic.quiz.ui.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 3/6/16.
 */
public class MultipleChoise extends BaseElement {
    private MultipleChoise.ViewHolder holder;
    public static final int CODE = 3;

    public MultipleChoise(QuestionDB questionDB) {
        answerLayout = R.layout.combo_box_answer;
        this.questionDB = questionDB;
    }

    public MultipleChoise() {
        answerLayout = R.layout.combo_box_answer;
        this.questionDB = new QuestionDB();
    }

    @Override
    public void extraOption(View view, String text, int position) {
        final LinearLayout container = (LinearLayout) ((View) view.getParent().getParent()).findViewById(R.id.optionContainerLL);
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        final View child = inflater.inflate(R.layout.option_row, null);
        container.addView(child);
        child.findViewById(R.id.trashBT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeView(child);
            }
        });
        (child.findViewById(R.id.optionET)).setOnFocusChangeListener(changeSaveListener(position));
        ((EditText) child.findViewById(R.id.optionET)).setText(text);
    }

    @Override
    public int getLayoutId() {
        return R.layout.multiple_choise_add_content;
    }

    @Override
    public boolean valid() {
        return true;
    }

    @Override
    public RecyclerView.ViewHolder getHolder(View itemView) {
        this.holder = new ViewHolder(itemView);
        return this.holder;
    }

    @Override
    public void bindHolder(RecyclerView.ViewHolder originHolder, int position) {
        MultipleChoise.ViewHolder holder = (MultipleChoise.ViewHolder) originHolder;
        ArrayList<Object> list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.DROP);
        holder.dropBTN.setTag(list);
        holder.dropBTN.setOnClickListener(listener);
        list = new ArrayList<>();
        list.add(0, position);
        list.add(1, Constant.EXTRA_OPTION);
        holder.addRowBTN.setTag(list);
        holder.addRowBTN.setOnClickListener(listener);
        holder.optionContainerLL.removeAllViews();
        if (questionDB != null) {
            holder.titleTV.setText(questionDB.getName());
            for (String option : questionDB.getOptions()) {
                Log.e(this.toString(), option);
                extraOption(holder.addRowBTN, option, position);
            }
            holder.maxLenght.setText(String.valueOf(questionDB.getMaxLenght()));
            holder.minLenght.setText(String.valueOf(questionDB.getMinLenght()));
        }
        holder.titleTV.setOnFocusChangeListener(changeSaveListener(position));
        holder.maxLenght.setOnFocusChangeListener(changeSaveListener(position));
        holder.minLenght.setOnFocusChangeListener(changeSaveListener(position));
    }

    @Override
    public void save(int order) {
        if (questionDB == null) questionDB = new QuestionDB();
        questionDB.setName(holder.titleTV.getText().toString());
        questionDB.setType(MultipleChoise.CODE);
        questionDB.setOrder(order);
        questionDB.setMaxLenght(Integer.parseInt(holder.maxLenght.getText().toString()));
        questionDB.setMaxLenght(Integer.parseInt(holder.minLenght.getText().toString()));
        List<String> options = new ArrayList<>();
        for (int index = 0; index < (holder.optionContainerLL).getChildCount(); ++index) {
            View nextChild = (holder.optionContainerLL).getChildAt(index);
            options.add(String.valueOf(((EditText) nextChild.findViewById(R.id.optionET)).getText()));
        }
        questionDB.setOptions(options);
        questionDB.save();
    }

    @Override
    protected void loadAnswerData(View v) {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText titleTV;
        public final Button dropBTN;
        public final Button addRowBTN;
        public final EditText maxLenght;
        public final EditText minLenght;
        public final LinearLayout optionContainerLL;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleTV = (EditText) view.findViewById(R.id.titleTV);
            dropBTN = (Button) view.findViewById(R.id.dropBTN);
            optionContainerLL = (LinearLayout) view.findViewById(R.id.optionContainerLL);
            addRowBTN = (Button) view.findViewById(R.id.addRowBTN);
            maxLenght = (EditText) view.findViewById(R.id.maxResponseTV);
            minLenght = (EditText) view.findViewById(R.id.minResponseET);
        }

    }
}
