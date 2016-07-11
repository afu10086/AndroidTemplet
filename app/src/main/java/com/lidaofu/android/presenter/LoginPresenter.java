package com.lidaofu.android.presenter;

import com.lidaofu.android.presenter.base.BasePresenter;
import com.lidaofu.android.presenter.base.BaseView;

/**
 * Created by LiDaofu on 16/7/10.
 */
public interface LoginPresenter extends BasePresenter{



    interface LoginView extends BaseView{
        /**
         * 添加view中需要的方法
         */
        public String getUserName();
        public String getUserPass();
    }



    /**
     * 添加presenter中需要的方法
     *
     */

    public void loginByHttp();



}
