package com.lidaofu.android.api.http;

import android.os.Handler;
import android.os.Looper;

import com.lidaofu.android.utils.NetManager;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by LiDaofu on 16/7/11.
 */
public class OkHttpManager {

    private static int HTTP_ERROR_CODE = 100;

    private static OkHttpManager instance;

    private OkHttpClient okHttpClient;

    private Handler handler;


    private OkHttpManager() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
    }

    public static OkHttpManager getManagerInstance() {
        if (instance == null) {
            instance = new OkHttpManager();
        }
        return instance;
    }


    /**
     * 参数信息的处理
     *
     * @param help
     * @return
     */
    private Request getRequest(HttpHelpListener help) {
        String url = help.getHttpUrl();
        if (!url.contains("http") && !url.contains("https")) {
            throw new IllegalArgumentException("url error:url not contain http or https");
        }
        Request.Builder builder = new Request.Builder();
        builder.tag(url).url(url);

        //添加http头信息
        List<HttpHelpListener.Params> heads = help.getHttpHead();
        for (HttpHelpListener.Params param : heads) {
            builder.addHeader(param.key, param.values);
        }

        String params = help.getHttpParams();
        RequestBody body = null;
        //添加参数信息
        if (params != null) {
            body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
        }
        switch (help.getHttpMethod()) {
            case GET:
                builder.get();
                break;
            case POST:
                builder.post(body);
                break;
            case DELETE:
                builder.delete(body);
                break;
            case PUT:
                builder.put(body);
                break;
            case HEAD:
                builder.head();
                break;
            case PATCH:
                builder.patch(body);
                break;
        }
        Request request = builder.build();
        return request;
    }


    private void httpExecute(Request request, final OkHttpListener listener) {

        if (!NetManager.isNetConnected()) {
            listener.onFail(HTTP_ERROR_CODE, null, new Exception("网络不可用"));
            return;
        }

        if (request == null) {
            listener.onFail(HTTP_ERROR_CODE, null, new Exception("request is null"));
            return;
        }


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFail(HTTP_ERROR_CODE, null, new Exception("加载失败"));
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {

                final String body = response.body().string();
                final Headers headers = response.headers();
                final int httpCode = response.code();
                if (response.isSuccessful()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess(httpCode, headers, body);
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onFail(httpCode, headers, new Exception("加载失败"));
                        }
                    });
                }

            }
        });

    }


    public static void httpDo(HttpHelpListener help, OkHttpListener listener) {
        Request request = getManagerInstance().getRequest(help);
        getManagerInstance().httpExecute(request, listener);
    }


}
