package com.develop.vic.quiz.database;

import com.develop.vic.quiz.database.AppDatabase;
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
 * Created by vic on 17/05/2016.
 */
@ModelContainer
@Table(database = AppDatabase.class)
public class QuestionDB extends BaseModel {
    @PrimaryKey (autoincrement = true)
    long id;
    @Column
    long timestamp;
    @Column
    String name;
    @Column
    int maxLenght;
    @Column
    int minLenght;
    @Column
    String Options;
    @Column
    long quiz;
    @Column
    int type;
    @Column
    int order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return (name != null) ? name : "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxLenght() {
        return maxLenght;
    }

    public void setMaxLenght(int maxLenght) {
        this.maxLenght = maxLenght;
    }

    public long getQuiz() {
        return quiz;
    }

    public void setQuiz(long quiz) {
        this.quiz = quiz;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getMinLenght() {
        return minLenght;
    }

    public void setMinLenght(int minLenght) {
        this.minLenght = minLenght;
    }

    public List<String> getOptions() {
        JSONArray jsArray = null;
        ArrayList<String> listdata = new ArrayList<String>();
        if (Options != null)
            try {
                jsArray = new JSONArray(Options);
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
        Options = jsArray.toString();
    }
}