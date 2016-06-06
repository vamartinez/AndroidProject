package com.develop.vic.quiz.components;

import com.develop.vic.quiz.ui.EditQuizActivity;
import com.develop.vic.quiz.ui.FormActivity;
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

    void inject(EditQuizActivity editQuizActivity);
    void inject(QuizListActivity quizListActivity);
    void inject(FormActivity formActivity);
    void inject(QuizDetailActivity quizDetailActivity);
    void inject(QuizDetailFragment quizDetailFragment);
}
