package com.lidaofu.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.lidaofu.android.R;

/**
 * Created by LiDaofu on 16/7/12.
 */
public class MainBarView extends CustomView implements View.OnClickListener{


    private static final java.lang.String TAG =MainBarView.class.getSimpleName();

    private String[] titles=new String[4];

    public interface BarViewClick{
        public void onClick(View v,int location);
    }

    private BarView bvOne,bvTwo,bvThree,bvFour;
    private BarViewClick listener;


    public MainBarView(Context context) {
        super(context);
    }

    public MainBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAttrs(AttributeSet attrs) {

    }

    public void setBarViewClick(BarViewClick listener){
        this.listener=listener;
    }

    @Override
    public void initView(LayoutInflater inflater) {
        inflater.inflate(R.layout.mainbar_layout_view, this, true);
        bvOne= (BarView)findViewById(R.id.bv_mainbar_one);
        bvTwo= (BarView)findViewById(R.id.bv_mainbar_two);
        bvThree= (BarView)findViewById(R.id.bv_mainbar_three);
        bvFour= (BarView)findViewById(R.id.bv_mainbar_four);

        bvOne.setOnClickListener(this);
        bvTwo.setOnClickListener(this);
        bvThree.setOnClickListener(this);
        bvFour.setOnClickListener(this);
        //默认第一个选中
        updateStatus("1");
    }



    @Override
    public void onClick(View v) {
        String location= (String) v.getTag();
        updateStatus(location);
        if(listener!=null){
            listener.onClick(v,Integer.parseInt(location));
        }
    }

    public void setBarViewTitle(int[] titles){
        if(titles==null||titles.length!=4)
            throw new IllegalArgumentException("titles parameters exception");

        if(titles!=null&&titles.length==4){
            bvOne.setTitle(titles[0]);
            bvTwo.setTitle(titles[1]);
            bvThree.setTitle(titles[2]);
            bvFour.setTitle(titles[3]);
        }
    }

    private void updateStatus(String location){
        bvOne.setBarViewStatus(false);
        bvTwo.setBarViewStatus(false);
        bvThree.setBarViewStatus(false);
        bvFour.setBarViewStatus(false);

        if(location.equals("1")){
            bvOne.setBarViewStatus(true);
        }else if(location.equals("2")){
            bvTwo.setBarViewStatus(true);
        }else if(location.equals("3")){
            bvThree.setBarViewStatus(true);
        }else if(location.equals("4")){
            bvFour.setBarViewStatus(true);
        }

    }
}
