package com.lidaofu.android.ui.fragment;

import android.os.Bundle;

import com.alibaba.fastjson.TypeReference;
import com.lidaofu.android.adapter.BaseViewAdapter;
import com.lidaofu.android.adapter.FourFragmentAdapter;
import com.lidaofu.android.api.Api;
import com.lidaofu.android.mode.Invest;
import com.lidaofu.android.mode.PagerInfo;
import com.lidaofu.android.ui.base.BaseListFragment;

import java.lang.reflect.Type;

/**
 * Created by LiDaofu on 16/7/11.
 */
public class FourFragment extends BaseListFragment {

    private int pager=1;

    @Override
    protected BaseViewAdapter getListAdapter() {
        return new FourFragmentAdapter(getActivity());
    }


    @Override
    protected String getFirstLoadUrl() {
        pager=1;
        return Api.listUrlTest+pager;
    }

    @Override
    protected String getLoadMoreUrl() {
        pager++;
        return Api.listUrlTest+pager;
    }


    @Override
    protected void setupListener() {

    }

    @Override
    protected void setupData() {

    }

    @Override
    protected void setupArgs(Bundle arguments) {

    }

    @Override
    public Type getType() {
        return new TypeReference<PagerInfo<Invest>>(){}.getType();
    }
}
