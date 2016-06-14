package com.develop.vic.quiz;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.develop.vic.quiz.ui.QuizListActivity;
import com.develop.vic.quiz.widget.WidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class QuizWidget extends AppWidgetProvider {

    public static String EXTRA_WORD =
            "com.develop.vic.quiz.widget";

    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        Log.e(this.toString(), "Widget UPDATE");
        for (int i = 0; i < appWidgetIds.length; i++) {
            Log.e(this.toString(), "Widget UPDATE FOR IN"+i);
            Intent svcIntent = new Intent(ctxt, WidgetService.class);

            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews widget = new RemoteViews(ctxt.getPackageName(),
                    R.layout.quiz_widget);

            widget.setRemoteAdapter(appWidgetIds[i], R.id.listViewWidget,
                    svcIntent);

            Intent clickIntent = new Intent(ctxt, QuizListActivity.class);
            PendingIntent clickPI = PendingIntent
                    .getActivity(ctxt, 0,
                            clickIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

            widget.setPendingIntentTemplate(R.id.listViewWidget, clickPI);

            appWidgetManager.updateAppWidget(appWidgetIds[i], widget);
        }

        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }


}

