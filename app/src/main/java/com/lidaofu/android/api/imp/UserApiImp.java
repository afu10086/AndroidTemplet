package com.lidaofu.android.api.imp;

import com.alibaba.fastjson.JSON;
import com.lidaofu.android.api.ApiListener;
import com.lidaofu.android.api.BaseApi;
import com.lidaofu.android.api.UserApi;
import com.lidaofu.android.api.http.HttpHelp;
import com.lidaofu.android.api.http.OkHttpListener;
import com.lidaofu.android.mode.User;
import com.squareup.okhttp.Headers;

/**
 * Created by LiDaofu on 16/7/11.
 */
public class UserApiImp extends BaseApi implements UserApi{


    @Override
    public void login(User user, final ApiListener<User> listener) {

        HttpHelp help=new HttpHelp(LoginUrl);
        httpManager.httpDo(help, new OkHttpListener() {
            @Override
            public void onSuccess(int httpStatu, Headers header, String response) {
                User user=null;
                try {
                    user = JSON.parseObject(response, User.class);
                }catch (Exception e){
                    listener.onFail("解析失败");
                }
                if(user!=null) {
                    listener.onSuccess(user);
                }
            }

            @Override
            public void onFail(int httpStatu, Headers header, Exception error) {
                listener.onFail(error.getMessage());
            }
        });

    }




    @Override
    public void register(User user, ApiListener<User> listener) {

    }
}
