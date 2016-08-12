package com.lidaofu.android.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lidaofu.android.api.http.HttpHelp;
import com.lidaofu.android.api.http.OkHttpListener;
import com.lidaofu.android.api.http.OkHttpManager;
import com.lidaofu.android.mode.Entity;
import com.lidaofu.android.mode.PagerInfo;
import com.squareup.okhttp.Headers;

import java.util.List;

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
    public <M extends Entity> void requestData(final String url, final ApiListener<PagerInfo<M>> listener){

        HttpHelp help=new HttpHelp(url);
        httpManager.httpDo(help, new OkHttpListener() {
            @Override
            public void onSuccess(int httpStatu, Headers header, String response) {
                PagerInfo pagerInfo=null;
                try {//fastjson泛型解析
                   pagerInfo=JSON.parseObject(response,new TypeReference <PagerInfo<List<M>>>(){});
                }catch (Exception e){
                    listener.onFail("解析失败");
                }
                if(pagerInfo!=null) {
                    listener.onSuccess(pagerInfo);
                }
            }

            @Override
            public void onFail(int httpStatu, Headers header, Exception error) {
                listener.onFail(error.getMessage());
            }
        });

    }




    

}
