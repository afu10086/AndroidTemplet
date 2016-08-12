package com.lidaofu.android.view.itemview;

/**
 * Created by LiDaofu on 16/8/12.
 *
 *自定义item view的基类
 *
 *
 */
public interface LayoutPresenter<T> {


    /**
     * 填充数据
     * @param t
     */
    public void update(T t);

}
