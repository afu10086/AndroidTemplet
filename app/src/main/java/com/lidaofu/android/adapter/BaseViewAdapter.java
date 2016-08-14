package com.lidaofu.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lidaofu.android.mode.Entity;
import com.lidaofu.android.utils.WarnException;
import com.lidaofu.android.view.itemview.LayoutPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiDaofu on 16/8/11.
 *
 * 基类Adapter
 *
 *
 */
public abstract class BaseViewAdapter<M extends Entity> extends BaseAdapter {


    private static final java.lang.String TAG =BaseViewAdapter.class.getSimpleName();

    protected Context context;
    private List<M> totalList;
    private LayoutInflater inflater;


    /**
     * 返回custom item view
     * @return
     */
    protected abstract int getLayoutId();


    public BaseViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据源
     *
     * @param list
     */
    public void setTotalList(List<M> list) {
        this.totalList = list;
        notifyDataSetChanged();
    }

    /**
     * 向数据源中添加一个集合数据
     *
     * @param data
     */
    public void addDataList(ArrayList<M> data) {
        if (totalList != null && data != null && !data.isEmpty()) {
            totalList.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 向数据源中添加一个新的数据
     *
     * @param item
     */
    public void addDataItem(M item) {
        if (totalList != null) {
            totalList.add(item);
        }
        notifyDataSetChanged();
    }

    /**
     * 向数据源的指定位置添加一个新的数据
     *
     * @param position
     * @param item
     */
    public void addDataItem(int position, M item) {
        if (totalList != null && position < totalList.size()) {
            totalList.add(position, item);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除一个数据
     *
     * @param item
     */
    public void removeDataItem(M item) {
        if (totalList != null) {
            totalList.remove(item);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除所有数据
     */
    public void removeDataAll() {
        if (totalList != null)
            totalList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = (totalList != null) ? totalList.size() : 0;
        return count;
    }

    @Override
    public M getItem(int position) {
        return (position >= 0 && totalList != null && position < totalList.size()) ? totalList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        M mode = getItem(position);
        final LayoutPresenter layoutPresenter;
        if(convertView==null){
            View itemView=inflater.inflate(getLayoutId(),parent,false);
            if(!(itemView instanceof LayoutPresenter)){
                throw new WarnException("ItemView must implement LayoutPresenter!");
            }else {
                layoutPresenter = (LayoutPresenter) inflater.inflate(getLayoutId(), parent, false);
            }
        }else{
            layoutPresenter= (LayoutPresenter) convertView;
        }
        layoutPresenter.update(mode);
        return (View) layoutPresenter;
    }


}
