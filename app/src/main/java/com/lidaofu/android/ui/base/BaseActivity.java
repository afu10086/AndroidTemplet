package com.lidaofu.android.ui.base;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.lidaofu.android.R;
import com.lidaofu.android.mode.SingleArray;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by LiDaofu on 16/7/10.
 */
public class BaseActivity extends AppCompatActivity {


    public Resources resources;
    public LayoutInflater inflater;

    //activity的堆栈管理
    public SingleArray singleArray=SingleArray.singleArrayInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources=getResources();
        inflater=LayoutInflater.from(this);
        singleArray.add(this);//将activity添加进队列
        setFullWindows();
    }


    /**
     * 设置状态条的颜色统一
     */
    private void setFullWindows(){
        Window windows=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            int color=(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)?resources.getColor(R.color.colorPrimary,null):resources.getColor(R.color.colorPrimary);
            windows.setStatusBarColor(color);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams winParams = windows.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            windows.setAttributes(winParams);

            //这个类是开源库提供
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        singleArray.remove(this);//从队列中移除activity
    }
}
