package com.lidaofu.android.api.http;

import com.squareup.okhttp.Headers;

/**
 * Created by LiDaofu on 16/7/11.
 */
public interface OkHttpListener {

    /**
     * 请求成功
     */
    public void onSuccess(int httpStatu,Headers header, String response);

    /**
     * 请求失败
     */
    public void onFail(int httpStatu,Headers header,Exception error);


}
