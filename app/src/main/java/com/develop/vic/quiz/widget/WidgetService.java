package com.develop.vic.quiz.widget;

/**
 * Created by Victor on 13/6/16.
 */

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return(new LoremViewsFactory(this.getApplicationContext(),
                intent));
    }
}