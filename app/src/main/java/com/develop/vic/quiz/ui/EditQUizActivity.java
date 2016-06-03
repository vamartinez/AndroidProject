package com.develop.vic.quiz.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.controler.QuizController;
import com.develop.vic.quiz.models.BaseElement;
import com.develop.vic.quiz.models.ComboBox;
import com.develop.vic.quiz.models.MultipleChoise;
import com.develop.vic.quiz.models.OpenText;
import com.develop.vic.quiz.ui.adapter.QuestionAdapter;
import com.develop.vic.quiz.ui.adapter.QuizAdapter;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EditQUizActivity extends BaseActivity {

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
        questionAdapter = new QuestionAdapter();
        //      ((RecyclerView)findViewById(R.id.dataRV)).setAdapter(new QuizAdapter());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuizController.createQuiz(titleTV.getText().toString(), descriptionTV.getText().toString());
                Toast.makeText(getApplicationContext(), R.string.quiz_update, Toast.LENGTH_LONG).show();
            }
        });
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


        QuizAdapter adapter = new QuizAdapter();
        dataRV.setAdapter(questionAdapter);

    }
}
