package com.lidaofu.android.adapter;

import android.content.Context;

import com.lidaofu.android.R;
import com.lidaofu.android.mode.Invest;

/**
 * Created by LiDaofu on 16/8/12.
 */
public class FourFragmentAdapter extends BaseViewAdapter<Invest> {

    public FourFragmentAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.item_view_invest;
    }


}
