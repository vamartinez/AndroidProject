package com.develop.vic.quiz.database;

import com.develop.vic.quiz.database.AppDatabase;
import com.develop.vic.quiz.interfaces.QuestionValidation;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 18/05/2016.
 */
@ModelContainer
@Table(database = AppDatabase.class)
public class AnswerDB extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;
    @Column
    long timestamp;
    @Column
    String response;
    @Column
    long question;
    @Column
    long form;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getQuestion() {
        return question;
    }

    public void setQuestion(long question) {
        this.question = question;
    }

    public long getForm() {
        return form;
    }

    public void setForm(long form) {
        this.form = form;
    }


    public List<String> getOptions() {
        JSONArray jsArray = null;
        ArrayList<String> listdata = new ArrayList<String>();
        if (response != null)
            try {
                jsArray = new JSONArray(response);
                if (jsArray != null) {
                    for (int i = 0; i < jsArray.length(); i++) {
                        listdata.add(jsArray.get(i).toString());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        return listdata;
    }

    public void setOptions(List<String> options) {
        JSONArray jsArray = new JSONArray(options);
        response = jsArray.toString();
    }
}

