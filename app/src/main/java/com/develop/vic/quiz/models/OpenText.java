package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vic on 18/05/2016.
 */
public class OpenText extends BaseElement {


    @Override
    public void valid() {

    }

    @Override
    protected RecyclerView.ViewHolder questionHolder() {
        return new ViewHolder();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder() {
            super(null);
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}


