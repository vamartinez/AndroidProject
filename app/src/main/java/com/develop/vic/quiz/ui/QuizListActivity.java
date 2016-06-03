package com.develop.vic.quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;


import com.develop.vic.quiz.R;
import com.develop.vic.quiz.ui.adapter.QuizAdapter;

/**
 * An activity representing a list of quizList. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link QuizDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class QuizListActivity extends BaseActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_quiz_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(),EditQUizActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.quiz_list);
        assert recyclerView != null;
        QuizAdapter adapter = new QuizAdapter();
        recyclerView.setAdapter(adapter);

        if (findViewById(R.id.quiz_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
          //  mTwoPane = true;
        }
    }

}
