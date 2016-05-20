package com.develop.vic.quiz.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.vic.quiz.App;
import com.develop.vic.quiz.R;
import com.develop.vic.quiz.components.ActivityComponent;
import com.develop.vic.quiz.components.DaggerActivityComponent;

/**
 * Created by Victor on 20/5/16.
 */
public class BaseFragment extends Fragment {
    private ActivityComponent mComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerActivityComponent.builder()
                .appComponent(getApp().getAppComponent()).build();
    }

    protected App getApp() {
        return (App) getActivity().getApplicationContext();
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

}
