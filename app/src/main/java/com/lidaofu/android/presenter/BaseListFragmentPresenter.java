package com.lidaofu.android.presenter;

import com.lidaofu.android.mode.PagerInfo;
import com.lidaofu.android.presenter.base.BasePresenter;
import com.lidaofu.android.presenter.base.BaseView;

/**
 * Created by LiDaofu on 16/7/10.
 */
public interface BaseListFragmentPresenter extends BasePresenter{

    interface BaseListFragmentView<M> extends BaseView{
        /**
         * 添加view中需要的方法
         */
        public String getHttpUrl();

        /**
         * 请求之前调用的方法
         */
        public void onPreExecute();

        /**
         * 请求完成后调用的方法
         */
        public void onPostExecute();

        /**
         * 加载失败
         */
        public void onLoadError();

        /**
         * 返回的数据
         * @param data
         */
        public void response(PagerInfo<M> data);
    }


    public  void loadData();


}
