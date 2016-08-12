package com.lidaofu.android.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lidaofu.android.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by lidaofu on 2015/11/6.
 */
public class ErrorView extends CustomView {


    public final static int ErrorView_NET = 0;//网络不可用
    public final static int ErrorView_EMPTY = 1;//数据空
    public final static int ErrorView_Error = 2;//未知原因
    public final static int ErrorView_Load=3;//正在加载
    public final static int ErrorView_GONE=4;//视图不可见


    private TextView tv_empty, tv_load;
    private AVLoadingIndicatorView progress;
    private boolean isLoad = true,isRest=false;
    private ErrorInterface listener;

    public interface ErrorInterface{
        public void restLoad();//重新加载
    }

    public void setErrorListener(ErrorInterface listener){
        this.listener=listener;
    }

    public ErrorView(Context context) {
        super(context);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ErrorView);
        isLoad = typedArray.getBoolean(R.styleable.ErrorView_isLoad, true);

    }

    @Override
    public void initView(LayoutInflater inflater) {
        inflater.inflate(R.layout.custom_view_error, this, true);
        tv_empty = (TextView) findViewById(R.id.tv_empty);
        tv_load = (TextView) findViewById(R.id.tv_load);
        progress = (AVLoadingIndicatorView) findViewById(R.id.avloading);
        if (isLoad) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }


        tv_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRest){
                    if(listener!=null){
                        listener.restLoad();
                    }
                }else{
                    //跳转到设置界面
                    Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                    context.startActivity(intent);
                }
            }
        });

    }

    public void setEmptyText(String text){
        tv_empty.setText(text);
    }

    public void setLoadText(String text){
        tv_load.setText(text);
    }

    public void setEmptyType(int type) {
        this.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        switch (type) {
            case ErrorView_EMPTY:
                tv_empty.setVisibility(VISIBLE);
                tv_load.setVisibility(GONE);
                break;
            case ErrorView_Error:
                tv_empty.setVisibility(GONE);
                tv_load.setVisibility(VISIBLE);
                tv_load.setText(R.string.string_errorview_reload);
                isRest=true;
                break;
            case ErrorView_NET:
                tv_empty.setVisibility(VISIBLE);
                tv_empty.setText(R.string.string_errorview_network_error);
                tv_load.setVisibility(VISIBLE);
                tv_load.setText(R.string.string_errorview_setting);
                isRest=false;
                break;
            case ErrorView_Load:
                tv_load.setVisibility(GONE);
                tv_empty.setVisibility(GONE);
                progress.setVisibility(VISIBLE);
                break;
            case ErrorView_GONE:
                this.setVisibility(View.GONE);
                break;
        }
    }


}
