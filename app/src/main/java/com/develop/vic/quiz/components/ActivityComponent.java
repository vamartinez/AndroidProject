package com.develop.vic.quiz.components;

import com.develop.vic.quiz.ui.EditQUizActivity;
import com.develop.vic.quiz.ui.QuizDetailActivity;
import com.develop.vic.quiz.ui.QuizDetailFragment;
import com.develop.vic.quiz.ui.QuizListActivity;

import dagger.Component;

/**
 * Created by Victor on 20/5/16.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent {

    void inject(EditQUizActivity editQUizActivity);
    void inject(QuizListActivity quizListActivity);
 //   void inject(QuizDetailActivity quizDetailActivity);
 //   void inject(QuizDetailFragment quizDetailFragment);
}
