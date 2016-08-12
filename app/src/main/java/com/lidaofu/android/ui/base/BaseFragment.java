package com.lidaofu.android.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidaofu.android.presenter.base.BaseView;

import butterknife.ButterKnife;

/**
 * Created by LiDaofu on 16/7/11.
 */
public abstract class BaseFragment extends Fragment implements BaseView {


    protected Bundle arguments;
    protected View contentView;
    private Context context;
    private BaseView baseView;


    protected abstract void setupView(View view);

    protected abstract void setupListener();

    protected abstract int getLayoutId();

    protected abstract void setupData();

    /**
     * 传递Bundler参数
     *
     * @param arguments
     */
    protected abstract void setupArgs(Bundle arguments);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            this.context = context;
            getActivity(context);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arguments = getArguments();
        setupArgs(arguments);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView != null) {
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if (parent != null) {
                parent.removeView(contentView);
            }
        } else {
            contentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, contentView);
            setupView(contentView);
        }
        setupListener();
        setupData();
        return contentView;
    }


    /**
     * 查找view
     *
     * @param viewId view的id
     * @param <V>    view的类型
     * @return
     */
    protected <V extends View> V findView(int viewId) {
        return (V) contentView.findViewById(viewId);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showProgress(String text) {
        if(baseView!=null){
            baseView.showProgress(text);
        }
    }

    @Override
    public void hideProgress(String text) {
        if(baseView!=null){
            baseView.hideProgress(text);
        }
    }

    @Override
    public void showText(String text) {
        if(baseView!=null){
            baseView.showText(text);
        }
    }

    private void getActivity(Context context) {
        if(context instanceof BaseView){
            baseView= (BaseView) context;
        }
    }


}
