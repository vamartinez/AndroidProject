package com.develop.vic.quiz.ui;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.vic.quiz.R;
import com.develop.vic.quiz.controler.QuizController;
import com.develop.vic.quiz.dummy.DummyContent;
import com.develop.vic.quiz.ui.adapter.QuizAdapter;
import com.develop.vic.quiz.ui.adapter.ResultAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class QuizDetailFragment extends BaseFragment {

    private int quizId;



    @Inject
    QuizController mQuizController;
    @InjectView(R.id.responseRV)
    RecyclerView recyclerView;

    public QuizDetailFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);

        if (getArguments().containsKey(Constant.QUIZ_ID)) {

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                Log.e(this.toString(), mQuizController.getQuizTitle() + " - ID result>>>" + getArguments().getInt(Constant.QUIZ_ID));
                quizId = getArguments().getInt(Constant.QUIZ_ID);
                mQuizController.setQuizId(quizId);
                appBarLayout.setTitle(mQuizController.getQuizTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quiz_detail, container, false);
        ButterKnife.inject(this, rootView);
        if (quizId != -1) {
            mQuizController.getList();
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            ResultAdapter adapter = new ResultAdapter();
            adapter.addAll(mQuizController.getList());
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }
}
