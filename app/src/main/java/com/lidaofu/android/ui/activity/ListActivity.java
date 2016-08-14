package com.lidaofu.android.ui.activity;

import com.alibaba.fastjson.TypeReference;
import com.lidaofu.android.R;
import com.lidaofu.android.adapter.BaseViewAdapter;
import com.lidaofu.android.adapter.FourFragmentAdapter;
import com.lidaofu.android.api.Api;
import com.lidaofu.android.mode.Invest;
import com.lidaofu.android.mode.PagerInfo;
import com.lidaofu.android.ui.base.BaseListActivity;

import java.lang.reflect.Type;

/**
 * Created by LiDaofu on 16/8/13.
 */
public class ListActivity extends BaseListActivity {


    private int pager=1;

    @Override
    protected BaseViewAdapter getListAdapter() {
        return  new FourFragmentAdapter(this);
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
    public Type getType() {
        return new TypeReference<PagerInfo<Invest>>(){}.getType();
    }

    @Override
    public void setupListener() {

    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.ListActivity);
    }
}
