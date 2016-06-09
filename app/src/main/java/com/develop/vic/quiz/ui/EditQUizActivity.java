package com.develop.vic.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.controler.QuizController;
import com.develop.vic.quiz.models.ComboBox;
import com.develop.vic.quiz.models.MultipleChoise;
import com.develop.vic.quiz.models.OpenText;
import com.develop.vic.quiz.ui.adapter.QuestionAdapter;


import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EditQuizActivity extends BaseActivity {

    @Inject
    QuizController mQuizController;

    @InjectView(R.id.titleTV)
    protected EditText titleTV;
    @InjectView(R.id.descriptionTV)
    protected EditText descriptionTV;
    @InjectView(R.id.fab)
    protected FloatingActionButton fab;
    @InjectView(R.id.textBTN)
    protected Button textBTN;
    @InjectView(R.id.multipleBTN)
    protected Button multipleBTN;
    @InjectView(R.id.cmbBTN)
    protected Button cmbBTN;

    @InjectView(R.id.question_list)
    protected RecyclerView dataRV;

    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_edit_quiz);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        questionAdapter = new QuestionAdapter();
        Intent intent = getIntent();
        long quizId = intent.getLongExtra(Constant.QUIZ_ID, -1L);
        if (quizId != -1) {
            mQuizController.setQuizId(quizId);
            titleTV.setText(mQuizController.getQuizTitle());
            descriptionTV.setText(mQuizController.getDescriptionQuiz());
            questionAdapter.addAll(mQuizController.getList());
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveQuiz();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionAdapter.add(new OpenText());
            }
        });
        multipleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionAdapter.add(new MultipleChoise());
            }
        });
        cmbBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionAdapter.add(new ComboBox());
            }
        });
        dataRV.setAdapter(questionAdapter);

    }

    private void saveQuiz() {
        titleTV.requestFocus();
        if (!(questionAdapter.getItemCount() > 0)) {
            Toast.makeText(getApplicationContext(), R.string.question_after_save, Toast.LENGTH_SHORT).show();
            return;
        }
        mQuizController.createQuiz(titleTV.getText().toString(), descriptionTV.getText().toString());
        mQuizController.addQuestion(questionAdapter.getData());
        Toast.makeText(getApplicationContext(), R.string.quiz_update, Toast.LENGTH_LONG).show();

    }
}
