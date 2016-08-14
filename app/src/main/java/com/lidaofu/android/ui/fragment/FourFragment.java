package com.lidaofu.android.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.fastjson.TypeReference;
import com.lidaofu.android.adapter.BaseViewAdapter;
import com.lidaofu.android.adapter.FourFragmentAdapter;
import com.lidaofu.android.api.Api;
import com.lidaofu.android.mode.Invest;
import com.lidaofu.android.mode.PagerInfo;
import com.lidaofu.android.ui.activity.ListActivity;
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

        listFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });

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
