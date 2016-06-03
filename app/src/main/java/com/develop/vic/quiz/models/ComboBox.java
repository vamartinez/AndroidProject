package com.develop.vic.quiz.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.ui.Constant;

import java.util.ArrayList;

/**
 * Created by Victor on 3/6/16.
 */
public class ComboBox extends BaseElement {
    @Override
    public void extraOption(View view) {
        LinearLayout container = (LinearLayout) ((View) view.getParent().getParent()).findViewById(R.id.optionContainerLL);
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.option_row, null);
        container.addView(child);
    }

    @Override
    public int getLayoutId() {
        return R.layout.combo_box_add_content;
    }

    @Override
    public void valid() {

    }

    @Override
    public RecyclerView.ViewHolder getHolder(View itemView, View.OnClickListener listener) {
        return new ComboBox.ViewHolder(itemView, listener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText titleTV;
        public final Button dropBTN;
        public final Button addRowBTN;
        public final LinearLayout optionContainerLL;
        private View.OnClickListener listener;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleTV = (EditText) view.findViewById(R.id.titleTV);
            dropBTN = (Button) view.findViewById(R.id.dropBTN);
            optionContainerLL = (LinearLayout) view.findViewById(R.id.optionContainerLL);
            addRowBTN = (Button) view.findViewById(R.id.addRowBTN);
        }

        public void bindHolder(RecyclerView.ViewHolder holder, int position){
            ArrayList<Object> list = new ArrayList<>();
            list.add(0, getAdapterPosition());
            list.add(1, Constant.DROP);
            dropBTN.setTag(list);
            dropBTN.setOnClickListener(listener);

            list = new ArrayList<>();
            list.add(0, getAdapterPosition());
            list.add(1, Constant.EXTRA_OPTION);
            addRowBTN.setTag(list);
            addRowBTN.setOnClickListener(listener);

        }

    }
}
