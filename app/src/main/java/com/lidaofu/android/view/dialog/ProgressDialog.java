
package com.lidaofu.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import com.lidaofu.android.R;

/**
 * Created by lidaofu on 2015/8/15.
 * eamil lidaofu_zlx@163.com
 */
public class ProgressDialog extends Dialog {

    private TextView tv_progress;
    private Context context;

    public ProgressDialog(Context context) {
        super(context);
        initView(context);
    }


    public ProgressDialog(Context context, int theme) {
        super(context, theme);
        initView(context);
    }

    protected ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }


    private void initView(Context context) {
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);//设置无标题
        getWindow().setBackgroundDrawable(new ColorDrawable());//设置无背景边框
        this.setCancelable(false);
        setContentView(R.layout.progress_dialog);
        tv_progress = (TextView) findViewById(R.id.tv_progress);

    }

    public void setProgressText(String text) {
        tv_progress.setText(text);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
        }
        return super.onKeyDown(keyCode, event);
    }
}