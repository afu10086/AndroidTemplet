package com.lidaofu.android.api;

import com.lidaofu.android.mode.User;

/**
 * Created by LiDaofu on 16/7/11.
 */
public interface UserApi{

    /**
     * 用户登录
     * @param user
     * @param listener
     */
    public void login(User user,ApiListener<User> listener);


    /**
     * 用户注册
     * @param user
     * @param listener
     */
    public void register(User user,ApiListener<User> listener);





}
