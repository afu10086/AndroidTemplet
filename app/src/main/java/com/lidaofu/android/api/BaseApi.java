package com.lidaofu.android.api;

import com.lidaofu.android.api.http.HttpHelp;
import com.lidaofu.android.api.http.OkHttpListener;
import com.lidaofu.android.api.http.OkHttpManager;
import com.squareup.okhttp.Headers;

/**
 * Created by LiDaofu on 16/7/11.
 */
public class BaseApi implements Api{


    public OkHttpManager httpManager;

    public BaseApi(){
        httpManager=OkHttpManager.getManagerInstance();
    }


    /**
     * 基本的请求数据方法
     * @param url 请求的url
     * @param listener
     */
    public void requestData(final String url, final ApiListener listener){

        HttpHelp help=new HttpHelp(url);
        httpManager.httpDo(help, new OkHttpListener() {
            @Override
            public void onSuccess(int httpStatu, Headers header, String response) {
                listener.onSuccess(response);
            }

            @Override
            public void onFail(int httpStatu, Headers header, Exception error) {
                listener.onFail(error.getMessage());
            }
        });

    }




    

}
