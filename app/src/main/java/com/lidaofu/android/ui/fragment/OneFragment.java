package com.lidaofu.android.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.lidaofu.android.R;

/**
 * Created by LiDaofu on 16/7/11.
 */
public class OneFragment extends MainFragment {

    @Override
    public View setupView(LayoutInflater inflater) {
        View view=inflater.inflate(R.layout.fragment_one,null);
        return view;
    }

    @Override
    public void setupListener() {

    }
}
