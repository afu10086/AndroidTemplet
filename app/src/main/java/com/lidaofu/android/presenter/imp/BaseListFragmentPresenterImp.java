package com.lidaofu.android.presenter.imp;

import com.alibaba.fastjson.JSON;
import com.lidaofu.android.api.ApiListener;
import com.lidaofu.android.api.BaseApi;
import com.lidaofu.android.mode.PagerInfo;
import com.lidaofu.android.presenter.BaseListFragmentPresenter;
import com.lidaofu.android.presenter.base.Presenter;
import com.lidaofu.android.utils.LogUtils;
import com.lidaofu.android.utils.StringUtils;

/**
 * Created by LiDaofu on 16/7/10.
 * <p>
 */
public class BaseListFragmentPresenterImp extends Presenter<BaseListFragmentPresenter.BaseListFragmentView> implements BaseListFragmentPresenter {

    private static final java.lang.String TAG = BaseListFragmentPresenterImp.class.getSimpleName();

    private BaseApi baseApi;

    public BaseListFragmentPresenterImp(BaseListFragmentPresenter.BaseListFragmentView baseListFragmentView) {
        super(baseListFragmentView);
        baseApi = new BaseApi();
    }


    @Override
    public void loadData() {
        String httpUrl = view.getHttpUrl();
        if (StringUtils.isBlank(httpUrl) || !httpUrl.contains("http"))
            return;
        view.onPostExecute();
        baseApi.requestData(httpUrl, new ApiListener<String>() {
            @Override
            public void onSuccess(String response) {
                PagerInfo pagerInfo = null;
                try {//fastjson泛型解析  new TypeReference<PagerInfo<M>>()
                    pagerInfo = JSON.parseObject(response,view.getType());
                } catch (Exception e) {
                    LogUtils.i(TAG, "解析失败");
                    view.onLoadError();
                }
                if (pagerInfo != null) {
                    view.response(pagerInfo);
                }
            }

            @Override
            public void onFail(String error) {
                view.onLoadError();
            }
        });
    }
}
