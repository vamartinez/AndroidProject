package com.develop.vic.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

import com.develop.vic.quiz.R;

/**
 * An activity representing a single QuizDB detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link QuizListActivity}.
 */
public class QuizDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_quiz_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            Log.e(this.toString()," - ID rORIGINAL esult>>>"+getIntent().getIntExtra(Constant.QUIZ_ID,-1));
            arguments.putInt(Constant.QUIZ_ID,
                    getIntent().getIntExtra(Constant.QUIZ_ID, -1));
            QuizDetailFragment fragment = new QuizDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.quiz_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, QuizListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
