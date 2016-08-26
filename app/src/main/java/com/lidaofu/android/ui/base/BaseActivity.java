package com.lidaofu.android.ui.base;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.github.johnpersano.supertoasts.SuperToast;
import com.lidaofu.android.mode.SingleArray;
import com.lidaofu.android.presenter.base.BaseView;
import com.lidaofu.android.utils.StringUtils;
import com.lidaofu.android.view.dialog.ProgressDialog;

/**
 * Created by LiDaofu on 16/7/10.
 */
public class BaseActivity extends AppCompatActivity implements BaseView{


    public Resources resources;
    public LayoutInflater inflater;

    private ProgressDialog progressDialog;
    private SuperToast superToast;


    //activity的堆栈管理
    public SingleArray singleArray=SingleArray.singleArrayInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources=getResources();
        inflater=LayoutInflater.from(this);
        progressDialog = new ProgressDialog(this);
        superToast = new SuperToast(this);
        singleArray.add(this);//将activity添加进队列
        setFullWindows();
    }


    /**
     * 设置状态条的颜色统一
     */
    private void setFullWindows(){

        View decorView = getWindow().getDecorView();
        //5.0以上设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            return;
        }
        //4.1以上设置
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN){
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            return;
        }





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
        singleArray.remove(this);//从队列中移除activity
    }
}
