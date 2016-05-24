package com.develop.vic.quiz.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.controler.QuizController;
import com.develop.vic.quiz.models.Quiz;

import javax.inject.Inject;

public class EditQUizActivity extends BaseActivity {

    @Inject
    QuizController mQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getComponent().inject(this);
        setContentView(R.layout.activity_edit_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              //  mQuiz.createQuiz("uno dos tres ","cuatro cinco seis site");
             //   quiz.save();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
