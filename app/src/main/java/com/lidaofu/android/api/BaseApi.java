package com.lidaofu.android.api;

import com.lidaofu.android.api.http.OkHttpManager;

/**
 * Created by LiDaofu on 16/7/11.
 */
public class BaseApi implements Api{


    public OkHttpManager httpManager;

    public BaseApi(){
        httpManager=OkHttpManager.getManagerInstance();
    }
    

}
