package com.lidaofu.android.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LiDaofu on 16/7/11.
 */
public abstract class BaseFragment extends Fragment {



    public abstract View setupView(LayoutInflater inflater);

    public abstract void setupListener();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = setupView(inflater);
        setupListener();
        return view;
    }
}
