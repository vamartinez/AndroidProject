package com.develop.vic.quiz.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;


import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.database.QuizDB_Table;
import com.develop.vic.quiz.ui.adapter.CustomCursorLoader;
import com.develop.vic.quiz.ui.adapter.QuizAdapter;
import com.develop.vic.quiz.ui.adapter.QuizCursorAdapter;
import com.raizlabs.android.dbflow.structure.provider.ContentUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * An activity representing a list of quizList. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link QuizDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class QuizListActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @InjectView(R.id.quiz_list)
    RecyclerView recyclerView;
    @Inject
    Context context;

    private QuizCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_quiz_list);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditQuizActivity.class);
                startActivity(intent);
            }
        });

        adapter = new QuizCursorAdapter(this);
        //QuizAdapter adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        getSupportLoaderManager().restartLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.e(this.toString(),"onCreateLoader");
        QuizDB quizDB= new QuizDB();
        //return ContentUtils.query(getContentResolver(), QuizDB.CONTENT_URI, QuizDB.);
        return new CustomCursorLoader(this,QuizDB.CONTENT_URI,null);
        //return new CursorLoader(this, QuizDB.CONTENT_URI, null, null, null, QuizDB_Table.description + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.e(this.toString(),"onLoaderFinish");
        if (adapter != null) {
            adapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.e(this.toString(),"onLOaderRest");
    }
}
