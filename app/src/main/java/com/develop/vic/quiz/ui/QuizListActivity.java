package com.develop.vic.quiz.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View;


import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.ui.adapter.QuizCursorAdapter;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.ArrayList;
import java.util.List;

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
public class QuizListActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<List<QuizDB>> {

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<List<QuizDB>> onCreateLoader(int id, Bundle args) {
        AsyncLoader asyncLoader = new AsyncLoader(getApplicationContext());
        asyncLoader.forceLoad();
        return asyncLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<QuizDB>> loader, List<QuizDB> data) {
        recyclerView.setAdapter(new QuizCursorAdapter(this, data));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<QuizDB>> loader) {
        recyclerView.setAdapter(null);
    }


    public static class AsyncLoader extends AsyncTaskLoader<List<QuizDB>> {

        List<QuizDB> mList;

        public AsyncLoader(Context context) {
            super(context);
            mList = new ArrayList<>();
        }

        @Override
        public List<QuizDB> loadInBackground() {
            mList = new ArrayList<QuizDB>();
            FlowCursorList<QuizDB> flowCursor = new FlowCursorList<>(false, QuizDB.class);
            for (int i = 0; i < flowCursor.getCount(); i++)
                mList.add(flowCursor.getItem(i));
            return mList;
        }

        @Override
        public void deliverResult(List<QuizDB> data) {
            super.deliverResult(data);
        }
    }

}
