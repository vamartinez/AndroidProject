package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.dummy.DummyContent;

/**
 * Created by vic on 18/05/2016.
 */
public class OpenText extends BaseElement {


    @Override
    public void valid() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.open_text_add_content;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView counterTV;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.titleTV);
            mContentView = (TextView) view.findViewById(R.id.descriptionTV);
            counterTV = (TextView) view.findViewById(R.id.counterTV);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}


