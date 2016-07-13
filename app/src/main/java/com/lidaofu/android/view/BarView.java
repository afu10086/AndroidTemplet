package com.lidaofu.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidaofu.android.R;
import com.lidaofu.android.utils.StringUtils;

/**
 * Created by LiDaofu on 16/7/12.
 */
public class BarView extends CustomView {


    private ImageView ivBarViewImage;
    private TextView tvBarViewText, tvBarViewRedText;
    private String strTitle;
    private int imageLight, imageNormal;
    private int titleSize;


    public BarView(Context context) {
        super(context);
    }

    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BarView_attrs);
        strTitle = typedArray.getString(R.styleable.BarView_attrs_text);
        imageLight = typedArray.getResourceId(R.styleable.BarView_attrs_bground_light, -1);
        imageNormal = typedArray.getResourceId(R.styleable.BarView_attrs_bground_normal, -1);
        titleSize = typedArray.getInt(R.styleable.BarView_attrs_str_size, 12);
    }

    @Override
    public void initView(LayoutInflater inflater) {
        inflater.inflate(R.layout.bv_layout_view, this, true);
        ivBarViewImage = (ImageView) findViewById(R.id.iv_barview_image);
        tvBarViewText = (TextView) findViewById(R.id.tv_barview_text);
        tvBarViewRedText = (TextView) findViewById(R.id.tv_barview_red_text);

        if(!StringUtils.isBlank(strTitle)) {
            tvBarViewText.setText(strTitle);
        }

        tvBarViewText.setTextSize(titleSize);
        if (imageNormal != -1) {
            ivBarViewImage.setImageResource(imageNormal);
        }
        tvBarViewRedText.setVisibility(View.GONE);
    }


    public void setBarViewStatus(boolean status) {
        int image = (status) ? imageLight : imageNormal;
        String color=(status)?"#EF5350":"#BDBDBD";

        ivBarViewImage.setImageResource(image);
        tvBarViewText.setTextColor(Color.parseColor(color));

    }

    public void setTitle(int title){
        tvBarViewText.setText(title);
    }

    public void setRedText(String text) {
        if (!StringUtils.isBlank(text)) {
            tvBarViewRedText.setText(text);
            tvBarViewRedText.setVisibility(View.VISIBLE);
        }else{
            tvBarViewRedText.setVisibility(View.GONE);
        }
    }

}
