package com.develop.vic.quiz.ui;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.controler.QuizController;
import com.develop.vic.quiz.dummy.DummyContent;

import javax.inject.Inject;


public class QuizDetailFragment extends BaseFragment {

    public QuizDetailFragment() {
        super();
    }

    @Inject
    QuizController mQuizController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        if (getArguments().containsKey(Constant.QUIZ_ID)) {

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                Log.e(this.toString(),mQuizController.getQuizTitle()+" - ID result>>>"+getArguments().getInt(Constant.QUIZ_ID));
                mQuizController.setQuizId(getArguments().getInt(Constant.QUIZ_ID));
                appBarLayout.setTitle(mQuizController.getQuizTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quiz_detail, container, false);
        mQuizController.getList();
        return rootView;
    }
}
