package com.develop.vic.quiz.widget;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.develop.vic.quiz.QuizWidget;
import com.develop.vic.quiz.R;
import com.develop.vic.quiz.database.QuizDB;
import com.develop.vic.quiz.ui.QuizListActivity;
import com.develop.vic.quiz.ui.adapter.QuizCursorAdapter;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.ArrayList;
import java.util.List;

public class LoremViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final FlowCursorList<QuizDB> flowCursor;
    private Context ctxt=null;
    private int appWidgetId;
    private Cursor mCursor;



    public LoremViewsFactory(Context ctxt, Intent intent) {
        this.ctxt=ctxt;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        flowCursor = new FlowCursorList<>(false, QuizDB.class);
    }

    @Override
    public void onCreate() {
        // no-op
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
        return (flowCursor == null) ? 0 :  flowCursor.getCount();

    }

    @Override
    public RemoteViews getViewAt(int position) {
        QuizDB quizDB = flowCursor.getItem(position);
        RemoteViews row=new RemoteViews(ctxt.getPackageName(),
                R.layout.quiz_list_content_basic);

        row.setTextViewText(R.id.titleTV, quizDB.getName());
        row.setTextViewText(R.id.descriptionTV, quizDB.getDescription());

        Intent i=new Intent();
        Bundle extras=new Bundle();

        extras.putString(QuizWidget.EXTRA_WORD, quizDB.getName());
        i.putExtras(extras);
        row.setOnClickFillInIntent(R.id.titleTV, i);
        return(row);
    }

    @Override
    public RemoteViews getLoadingView() {
        return(null);
    }

    @Override
    public int getViewTypeCount() {
        return(1);
    }

    @Override
    public long getItemId(int position) {
        return(position);
    }

    @Override
    public boolean hasStableIds() {
        return(true);
    }



}