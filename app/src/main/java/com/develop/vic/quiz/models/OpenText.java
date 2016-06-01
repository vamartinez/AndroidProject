package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        public final EditText titleTV;
        public final EditText maxLenght;
        public final Button dropBTN;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleTV = (EditText) view.findViewById(R.id.titleTV);
            maxLenght = (EditText) view.findViewById(R.id.maxLenght);
            dropBTN = (Button) view.findViewById(R.id.dropBTN);
            //dropBTN.setOnClickListener(this.drop);

        }
    }

}


