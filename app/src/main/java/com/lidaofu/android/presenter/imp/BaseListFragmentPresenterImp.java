package com.lidaofu.android.presenter.imp;

import com.lidaofu.android.api.ApiListener;
import com.lidaofu.android.api.BaseApi;
import com.lidaofu.android.mode.Entity;
import com.lidaofu.android.mode.PagerInfo;
import com.lidaofu.android.presenter.BaseListFragmentPresenter;
import com.lidaofu.android.presenter.base.Presenter;
import com.lidaofu.android.utils.StringUtils;

/**
 * Created by LiDaofu on 16/7/10.
 * <p>
 */
public class BaseListFragmentPresenterImp<M extends Entity> extends Presenter<BaseListFragmentPresenter.BaseListFragmentView> implements BaseListFragmentPresenter {

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
        baseApi.requestData(httpUrl, new ApiListener<PagerInfo<M>>() {
            @Override
            public void onSuccess(PagerInfo<M> pagerInfo) {
                view.response(pagerInfo);
                view.onPostExecute();
            }

            @Override
            public void onFail(String error) {
                view.onLoadError();
            }
        });
    }
}
