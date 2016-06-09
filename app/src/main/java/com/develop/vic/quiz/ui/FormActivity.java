package com.develop.vic.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.controler.QuizController;
import com.develop.vic.quiz.models.BaseElement;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FormActivity extends BaseActivity {
    @InjectView(R.id.containerLL)
    protected LinearLayout containerLL;

    @Inject
    QuizController mQuizController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_form);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int quizId = intent.getIntExtra(Constant.QUIZ_ID, -1);
        if (quizId != -1) {
            mQuizController.setQuizId(quizId);
            setTitle(mQuizController.getQuizTitle());
        }
        final ArrayList<BaseElement> elementList = mQuizController.getList();
        for(int i=0;i<elementList.size();i++){
            containerLL.addView(elementList.get(i).getAnswerView(getApplicationContext()));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuizController.saveResponse(elementList);
                Snackbar.make(view, R.string.response_complete_message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
