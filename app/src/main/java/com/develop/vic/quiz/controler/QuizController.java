package com.develop.vic.quiz.controler;

import android.content.Context;
import android.util.Log;

import com.develop.vic.quiz.components.AppComponent;
import com.develop.vic.quiz.database.FormDB;
import com.develop.vic.quiz.database.QuestionDB;

import com.develop.vic.quiz.database.QuestionDB_Table;
import com.develop.vic.quiz.models.BaseElement;
import com.develop.vic.quiz.models.ComboBox;
import com.develop.vic.quiz.models.MultipleChoise;
import com.develop.vic.quiz.models.OpenText;
import com.develop.vic.quiz.models.Quiz;
import com.raizlabs.android.dbflow.sql.language.SQLite;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by vic on 23/05/2016.
 */
public class QuizController {
    @Inject
    Context mAppContext;
    @Inject
    transient Quiz mQuiz;

    public QuizController(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void createQuiz(String name, String description) {
        mQuiz.setData(name, description);
        mQuiz.save();
    }

    public void addQuestion(ArrayList<BaseElement> list) {
        int i = 0;
        for (BaseElement question : list) {
            question.persistQuestion(mQuiz.getId());
        }


    }


    public void setQuizId(long quizId) {
        this.mQuiz.setID(quizId);
    }

    public ArrayList<BaseElement> getList() {
        ArrayList<BaseElement> list = new ArrayList<>();
        List<QuestionDB> questionDBList = SQLite.select()
                .from(QuestionDB.class)
                .where(QuestionDB_Table.quiz.eq(mQuiz.getId()))
                .queryList();
        if (questionDBList != null) {
            for (int i = 0; i < questionDBList.size(); i++) {
                QuestionDB objectDB = questionDBList.get(i);
                switch (objectDB.getType()) {
                    case OpenText.CODE:
                        list.add(new OpenText(objectDB));
                        break;
                    case ComboBox.CODE:
                        list.add(new ComboBox(objectDB));
                        break;
                    case MultipleChoise.CODE:
                        list.add(new MultipleChoise(objectDB));
                        break;
                    default:
                        break;
                }
            }
        }

        return list;
    }

    public String getQuizTitle() {
        return this.mQuiz.getTitle();
    }

    public String getDescriptionQuiz() {
        return this.mQuiz.getDescription();
    }

    public void saveResponse(ArrayList<BaseElement> baseElements) {
        FormDB form = new FormDB();
        form.setQuiz(mQuiz.getId());
        form.save();
        for (BaseElement element : baseElements) {
            element.saveAnswer(form.getId());
        }

    }
}
