package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.ui.Constant;

/**
 * Created by Victor on 3/6/16.
 */
public class MultipleChoise extends BaseElement {
    @Override
    public void extraOption(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.multiple_choise_add_content;
    }

    @Override
    public void valid() {

    }
    @Override
    public RecyclerView.ViewHolder getHolder(View itemView, View.OnClickListener listener) {
        return new MultipleChoise.ViewHolder(itemView, listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText titleTV;
        public final EditText maxLenght;
        public final Button dropBTN;

        public ViewHolder(View view, View.OnClickListener listener) {
            super(view);
            mView = view;
            titleTV = (EditText) view.findViewById(R.id.titleTV);
            maxLenght = (EditText) view.findViewById(R.id.maxLenght);
            dropBTN = (Button) view.findViewById(R.id.dropBTN);
            dropBTN.setTag(getAdapterPosition(), Constant.DROP);
            dropBTN.setOnClickListener(listener);

        }
    }
}
