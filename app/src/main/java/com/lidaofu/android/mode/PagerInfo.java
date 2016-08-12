package com.lidaofu.android.mode;

import java.util.List;

/**
 * Created by LiDaofu on 16/8/11.
 */
public class PagerInfo<M> extends Entity{

    private List<M> pagerInfos;

    public List<M> getPagerInfos() {
        return pagerInfos;
    }

    public void setPagerInfos(List<M> pagerInfos) {
        this.pagerInfos = pagerInfos;
    }
}
