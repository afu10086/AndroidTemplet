package com.lidaofu.android.ui.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.ListView;

import com.lidaofu.android.R;
import com.lidaofu.android.adapter.BaseViewAdapter;
import com.lidaofu.android.mode.PagerInfo;
import com.lidaofu.android.presenter.BaseListFragmentPresenter;
import com.lidaofu.android.presenter.imp.BaseListFragmentPresenterImp;
import com.lidaofu.android.view.ErrorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


/**
 * Created by LiDaofu on 16/8/13.
 *
 * 包含ListView 刷新,加载更多的一个Activity基类,与BaseListFragment一样
 * 如果页面是一个ListView可以继承此基类,实现相关方法即可
 * eg:ListActivity
 *
 */
public abstract class BaseListActivity extends ToolbarActivity implements PtrHandler, LoadMoreHandler, ErrorView.ErrorInterface, BaseListFragmentPresenter.BaseListFragmentView {

    @Bind(R.id.list_fragment)
    ListView listFragment;
    @Bind(R.id.ptr_frame_load_more)
    LoadMoreListViewContainer loadMoreListViewContainer;
    @Bind(R.id.ptr_frame_list)
    PtrClassicFrameLayout ptrFrameList;
    @Bind(R.id.error_view)
    ErrorView errorView;


    protected BaseViewAdapter baseAdapter;
    protected ArrayList totalList;
    private boolean isFirstLoad = false;
    private BaseListFragmentPresenter presenter;

    /**
     * 返回list的adapter
     *
     * @return
     */
    protected abstract BaseViewAdapter getListAdapter();

    /**
     * 第一次加载时的url
     * @return
     */
    protected abstract String getFirstLoadUrl();

    /**
     * 加载更多的url
     * @return
     */
    protected abstract String getLoadMoreUrl();



    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void setupView() {
        setContentView(R.layout.fragment_base_list);
        ButterKnife.bind(this);

        ptrFrameList.setLoadingMinTime(500);
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString(getResources().getString(R.string.app_name), 32);
        int color = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ? getResources().getColor(R.color.colorPrimary, null) : getResources().getColor(R.color.colorPrimary);
        header.setTextColor(color);

        ptrFrameList.setHeaderView(header);
        ptrFrameList.addPtrUIHandler(header);
        ptrFrameList.setPtrHandler(this);

        loadMoreListViewContainer.useDefaultHeader();
        loadMoreListViewContainer.setLoadMoreHandler(this);

        baseAdapter = getListAdapter();
        listFragment.setAdapter(baseAdapter);
        totalList = new ArrayList<>();
        baseAdapter.setTotalList(totalList);

        errorView.setErrorListener(this);
        errorView.setEmptyType(ErrorView.ErrorView_Load);

        presenter = new BaseListFragmentPresenterImp(this);
        presenter.loadData();
        isFirstLoad = true;

    }

    @Override
    public String getHttpUrl() {
        return (isFirstLoad)?getFirstLoadUrl():getLoadMoreUrl();
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, listFragment, header);
    }

    /**
     * 刷新数据
     *
     * @param frame
     */
    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        if(totalList!=null){
            isFirstLoad=true;
            baseAdapter.removeDataAll();
            presenter.loadData();
        }
    }

    /**
     * 加载更多
     *
     * @param loadMoreContainer
     */
    @Override
    public void onLoadMore(LoadMoreContainer loadMoreContainer) {
        if(presenter!=null){
            presenter.loadData();
        }
    }

    @Override
    public void onPreExecute() {
        if (isFirstLoad) {
            errorView.setEmptyType(ErrorView.ErrorView_Load);
        }
    }

    @Override
    public void onPostExecute() {

    }

    @Override
    public void onLoadError() {
        if(isFirstLoad){
            errorView.setEmptyType(ErrorView.ErrorView_Error);
        }
    }

    /**
     * 数据加载完成,返回数据
     * @param data
     */
    @Override
    public void response(PagerInfo data) {
        ptrFrameList.refreshComplete();//刷新完成
        if (isFirstLoad && data != null) {
            if (data.getList()==null||data.getList().size() == 0) {
                errorView.setEmptyType(ErrorView.ErrorView_EMPTY);
                isFirstLoad = true;
            } else {
                errorView.setEmptyType(ErrorView.ErrorView_GONE);
                isFirstLoad = false;
            }
        }
        List list = data.getList();
        boolean hasMore=(!isFirstLoad&&(list==null)||list.size()==0)?false:true;
        //设置是否加载更多
        loadMoreListViewContainer.loadMoreFinish(list.isEmpty(),hasMore);
        if (list != null && list.size() != 0) {
            totalList.addAll(list);
        }
        baseAdapter.notifyDataSetChanged();
    }

    /**
     * 加载失败,点击重新加载
     */
    @Override
    public void restLoad() {
        if(presenter!=null){
            presenter.loadData();
        }
    }

}
