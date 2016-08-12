package com.lidaofu.android.api.http;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiDaofu on 16/7/11.
 */
public class HttpHelp implements HttpHelpListener {

    private String httpParams;
    private String httpUrl;
    private HttpMethod httpMethod;
    private List<Params> heads=new ArrayList<>();

    public HttpHelp(String httpUrl){
        this.httpUrl=httpUrl;
        this.httpMethod=HttpMethod.GET;
    }

    public HttpHelp(String httpUrl,HttpMethod method){
        this.httpUrl=httpUrl;
        this.httpMethod=method;
    }

    public HttpHelp(String httpUrl,HttpMethod method,String httpParams){
        this(httpUrl,method);
        this.httpParams=httpParams;
    }

    @Override
    public String getHttpUrl() {
        return this.httpUrl;
    }

    @Override
    public void setHttpUrl(String httpUrl) {
        this.httpUrl=httpUrl;
    }

    @Override
    public String getHttpParams() {
        return this.httpParams;
    }

    @Override
    public void setHttpParams(String httpParams) {
        this.httpParams=httpParams;
    }

    @Override
    public void setHttpMethod(HttpMethod method) {
        this.httpMethod=method;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    @Override
    public void setHttpHead(List<Params> httpHead) {
        heads.addAll(httpHead);
    }


    @Override
    public List<Params> getHttpHead() {
        return null;
    }
}
