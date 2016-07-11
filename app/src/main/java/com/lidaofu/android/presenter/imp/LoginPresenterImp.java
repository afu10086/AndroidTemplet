package com.lidaofu.android.presenter.imp;

import com.lidaofu.android.api.ApiListener;
import com.lidaofu.android.api.UserApi;
import com.lidaofu.android.api.imp.UserApiImp;
import com.lidaofu.android.mode.User;
import com.lidaofu.android.presenter.LoginPresenter;
import com.lidaofu.android.presenter.base.Presenter;
import com.lidaofu.android.utils.StringUtils;

/**
 * Created by LiDaofu on 16/7/10.
 *
 * presenter类中实现了LoginPresenter中的一些方法
 * loginPresenterImp类可以复写需要的方法
 *
 */
public class LoginPresenterImp extends Presenter<LoginPresenter.LoginView> implements LoginPresenter {

    private UserApi userApi;

    public LoginPresenterImp(LoginView loginView){
        super(loginView);
        userApi=new UserApiImp();
    }


    @Override
    public void loginByHttp() {

        String name=view.getUserName();
        String pass=view.getUserPass();
        if(StringUtils.isBlank(name)||StringUtils.isBlank(pass)){
            view.showText("用户名或密码不能为空");
            return;
        }

        view.showProgress("正在登录");
        User user=new User(name,pass);
        userApi.login(user, new ApiListener<User>() {
            @Override
            public void onSuccess(User user) {
                view.hideProgress("");
                view.showText("登录成功");

            }

            @Override
            public void onFail(String error) {
                view.hideProgress("");
                view.showText(error);
            }
        });

    }
}
