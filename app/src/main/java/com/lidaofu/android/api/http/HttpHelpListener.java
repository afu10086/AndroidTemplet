package com.lidaofu.android.api.http;

import java.util.List;

/**
 * Created by LiDaofu on 16/7/11.
 */
public interface HttpHelpListener {

    public enum HttpMethod{
        GET,POST,PUT,PATCH,DELETE,HEAD;
    }

    public static class Params {
        String key;
        String values;
        public Params() {
        }

        public Params(String key, String values) {
            this.key = key;
            this.values = values;
        }
    }

    public String getHttpUrl();

    public void setHttpUrl(String httpUrl);

    public String getHttpParams();

    public void setHttpParams(String httpParams);

    public void setHttpMethod(HttpMethod method);

    public HttpMethod getHttpMethod();

    public void setHttpHead(List<Params> httpHead);


    /**
     * 根据服务端的要求来添加需要的头信息
     * @return
     */
    public List<Params> getHttpHead();


}
