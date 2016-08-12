package com.lidaofu.android.ui.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidaofu.android.R;

import butterknife.ButterKnife;

/**
 * Created by LiDaofu on 16/7/11.
 */
public abstract  class ToolbarActivity extends BaseActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toobar);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
