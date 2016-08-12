package com.lidaofu.android.view.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.lidaofu.android.R;
import com.lidaofu.android.mode.Invest;
import com.lidaofu.android.view.CustomView;

/**
 * Created by LiDaofu on 16/8/12.
 */
public class InvestView extends CustomView implements LayoutPresenter<Invest> {


    TextView tvItemName;
    TextView tvItemId;
    TextView tvItemTime;

    public InvestView(Context context) {
        super(context);
    }

    public InvestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InvestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAttrs(AttributeSet attrs) {

    }

    @Override
    public void initView(LayoutInflater inflater) {
        inflater.inflate(R.layout.fragment_list_item_four, this,true);
        tvItemId= (TextView) findViewById(R.id.tv_item_id);
        tvItemTime= (TextView) findViewById(R.id.tv_item_time);
        tvItemName= (TextView) findViewById(R.id.tv_item_name);
    }


    @Override
    public void update(Invest invest) {
        tvItemId.setText(invest.getId());
        tvItemName.setText(invest.getName());
        tvItemTime.setText(invest.getCreated());
    }
}
