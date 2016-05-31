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
import com.develop.vic.quiz.models.OpenText;
import com.develop.vic.quiz.ui.adapter.QuestionAdapter;


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
    @InjectView(R.id.dataRV)
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
        ArrayList<BaseElement> lista = new ArrayList<>();
        questionAdapter = new QuestionAdapter(lista);
        lista.add(new OpenText());
        lista.add(new OpenText());
        lista.add(new OpenText());
        lista.add(new OpenText());
        dataRV.setAdapter(questionAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuizController.createQuiz(titleTV.getText().toString(), descriptionTV.getText().toString());
                Toast.makeText(getApplicationContext(), R.string.quiz_update, Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(this.toString(),"elemento agregado");
                questionAdapter.add(new OpenText());
                questionAdapter.notifyItemInserted(questionAdapter.getItemCount()-1);
            }
        });
    }
}
