package com.lidaofu.android.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.lidaofu.android.R;
import com.lidaofu.android.presenter.base.BaseView;
import com.lidaofu.android.utils.StringUtils;
import com.lidaofu.android.view.dialog.ProgressDialog;

import butterknife.ButterKnife;

/**
 * Created by LiDaofu on 16/7/11.
 */
public abstract  class ToolbarActivity extends BaseActivity implements BaseView {

    /**
     * 初始化view
     */
    public abstract void setupView();

    /**
     * listener 监听
     */
    public abstract void setupListener();

    public TextView toolbar;
    private LinearLayout linearContent;
    private ProgressDialog progressDialog;
    private SuperToast superToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toobar);
        progressDialog = new ProgressDialog(this);
        superToast = new SuperToast(this);
        setupView();
        setupListener();
    }


    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.activity_toobar) {
            super.setContentView(layoutResID);
            linearContent = (LinearLayout) findViewById(R.id.lineContent);
            toolbar = (TextView) findViewById(R.id.tv_toolbar_title);
            return;
        }
        View view = inflater.inflate(layoutResID, null);
        linearContent.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }


    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        linearContent.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }


    @Override
    public void setContentView(View view) {
        linearContent.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 显示progress
     * @param text
     */
    public void showProgress(String text) {
        if(progressDialog!=null&&!progressDialog.isShowing()){
            if(!StringUtils.isBlank(text))
                progressDialog.setProgressText(text);
            progressDialog.show();
        }
    }

    /**
     * 隐藏progress
     * @param text
     */
    public void hideProgress(String text) {
        if(progressDialog!=null&&progressDialog.isShowing()){
            if(!StringUtils.isBlank(text))
                progressDialog.setProgressText(text);
            progressDialog.hide();
        }
    }

    /**
     * 显示toast
     * @param text
     */
    public void showText(String text) {
        if (StringUtils.isBlank(text))
            return;
        SuperToast.cancelAllSuperToasts();
        superToast.setAnimations(SuperToast.Animations.FLYIN);
        superToast.setDuration(SuperToast.Duration.SHORT);
        superToast.setTextColor(Color.parseColor("#ffffff"));
        superToast.setTextSize(SuperToast.TextSize.SMALL);
        superToast.setBackground(SuperToast.Background.BLUE);
        superToast.setText(text);
        superToast.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
