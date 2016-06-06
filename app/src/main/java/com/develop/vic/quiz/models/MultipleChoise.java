package com.develop.vic.quiz.models;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.AnswerDB;
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
    private int countCheckselected;

    public MultipleChoise(QuestionDB questionDB) {
        answerLayout = R.layout.multiple_choise_answer;
        this.questionDB = questionDB;
    }

    public MultipleChoise() {
        answerLayout = R.layout.multiple_choise_answer;
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
        }
        holder.titleTV.setOnFocusChangeListener(changeSaveListener(position));
        holder.maxLenght.setOnFocusChangeListener(changeSaveListener(position));
    }

    @Override
    public void save(int order) {
        if (questionDB == null) questionDB = new QuestionDB();
        questionDB.setName(holder.titleTV.getText().toString());
        questionDB.setType(MultipleChoise.CODE);
        questionDB.setOrder(order);
        questionDB.setMaxLenght(Integer.parseInt(holder.maxLenght.getText().toString()));
        List<String> options = new ArrayList<>();
        for (int index = 0; index < (holder.optionContainerLL).getChildCount(); ++index) {
            View nextChild = (holder.optionContainerLL).getChildAt(index);
            options.add(String.valueOf(((EditText) nextChild.findViewById(R.id.optionET)).getText()));
        }
        questionDB.setOptions(options);
    }

    @Override
    protected void loadAnswerData(View v) {
        ((TextView) v.findViewById(R.id.questionTV)).setText(questionDB.getName());
        for (String option : questionDB.getOptions()) {
            final CheckBox cb = new CheckBox(v.getContext());
            cb.setText(option);
            ((ViewGroup) v.findViewById(R.id.checkboxContainerLL)).addView(cb);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && countCheckselected >= questionDB.getMaxLenght()) {
                        cb.setChecked(false);
                    } else {
                        if (isChecked) {
                            countCheckselected++;
                        } else {
                            countCheckselected--;
                        }
                    }
                }
            });
        }

    }

    @Override
    public void saveAnswer(long formId) {
        if (answerDB == null) answerDB = new AnswerDB();
        answerDB.setForm(formId);
        answerDB.setQuestion(questionDB.getId());
        ViewGroup container = ((ViewGroup) answerView.findViewById(R.id.checkboxContainerLL));
        List<String> selected = new ArrayList<>();
        for (int i = 0; i < container.getChildCount(); i++) {
            if (((CheckBox) container.getChildAt(i)).isChecked())
                selected.add(String.valueOf(i));
        }
        answerDB.setOptions(selected);
        answerDB.save();
    }

    @Override
    public RecyclerView.ViewHolder getResponseHolder(View view) {
        return null;
    }

    @Override
    public void bindResponseHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText titleTV;
        public final Button dropBTN;
        public final Button addRowBTN;
        public final EditText maxLenght;
        public final LinearLayout optionContainerLL;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleTV = (EditText) view.findViewById(R.id.titleTV);
            dropBTN = (Button) view.findViewById(R.id.dropBTN);
            optionContainerLL = (LinearLayout) view.findViewById(R.id.optionContainerLL);
            addRowBTN = (Button) view.findViewById(R.id.addRowBTN);
            maxLenght = (EditText) view.findViewById(R.id.maxResponseTV);
        }

    }
}
