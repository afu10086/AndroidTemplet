package com.lidaofu.android.api;

/**
 * Created by LiDaofu on 16/7/11.
 */
public interface ApiListener<T>{

    public void onSuccess(T t);

    public void onFail(String error);


}
