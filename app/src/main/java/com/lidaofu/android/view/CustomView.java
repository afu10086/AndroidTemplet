package com.lidaofu.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by lidaofu on 2015/4/28.
 * 自定义view的基类
 */
public abstract class CustomView extends LinearLayout {

    private static final String TAG ="MyCustomView";

    public Context context;
    public LayoutInflater inflater;

    public CustomView(Context context) {
        super(context);
        init(context);
        initView(inflater);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setAttrs(attrs);
        initView(inflater);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setAttrs(attrs);
        initView(inflater);
    }


    public void init(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }


    public abstract void setAttrs(AttributeSet attrs);
    public abstract void initView(LayoutInflater inflater);


}
