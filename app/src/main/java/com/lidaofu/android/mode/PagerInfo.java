package com.lidaofu.android.mode;

import java.util.ArrayList;

/**
 * Created by LiDaofu on 16/8/11.
 */
public class PagerInfo<M> extends Entity{


    private ArrayList<M> list = new ArrayList<M>();


    public ArrayList<M> getList() {
        return list;
    }

    public void setList(ArrayList<M> list) {
        this.list = list;
    }
}
