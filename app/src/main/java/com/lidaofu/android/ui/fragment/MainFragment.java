package com.lidaofu.android.ui.fragment;

import android.content.Context;

import com.lidaofu.android.ui.base.BaseFragment;
import com.lidaofu.android.ui.base.ToolbarActivity;
import com.lidaofu.android.utils.StringUtils;

/**
 * Created by LiDaofu on 16/7/11.
 */
public abstract class MainFragment extends BaseFragment {

    private ToolbarActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (ToolbarActivity) getActivity();
    }

    /**
     * 显示progress
     * @param text
     */
    public void showProgress(String text) {
       if(activity!=null){
           activity.showProgress(text);
       }
    }

    /**
     * 隐藏progress
     * @param text
     */
    public void hideProgress(String text) {
       if(activity!=null){
           activity.hideProgress(text);
       }
    }

    /**
     * 显示toast
     * @param text
     */
    public void showText(String text) {
        if (StringUtils.isBlank(text))
            return;
        if(activity!=null){
            activity.showText(text);
        }
    }


}
