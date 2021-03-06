package com.easychange.admin.easychangemerchant.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.EasyApplication;
import com.easychange.admin.easychangemerchant.R;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

public class WanRecyclerView extends FrameLayout implements SpringView.OnFreshListener {

    private View mView;

    private SpringView mSpringView;

    private RecyclerView mRecyclerView;

    private PullRecyclerViewCallBack callBack;

    private TextView stateView;

    private Context mContext;

    private boolean isRefresh = false;

    private boolean isLoadMore = false;

    public void setPullRecyclerViewListener(PullRecyclerViewCallBack callBack) {
        this.callBack = callBack;
    }

    public WanRecyclerView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public WanRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;

        mView = LayoutInflater.from(context).inflate(R.layout.view_recyclerview_wan, this);

        mSpringView = mView.findViewById(R.id.view_springView);
        mRecyclerView = mView.findViewById(R.id.view_recyclerView);
        stateView = mView.findViewById(R.id.view_state);

        mRecyclerView.setOnTouchListener(new TouchRecyclerView());
        mSpringView.setOnTouchListener(new TouchRecyclerView());

        mSpringView.setListener(this);
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setHeader(new RefreshHeader(mContext));
        mSpringView.setFooter(new RefreshFooter(mContext));
        mSpringView.setEnableFooter(false);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 线性布局管理器
     */
    public void setLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * 网格布局管理器
     */
    public void setGridLayout(int spanCount) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * 交错网格布局管理器
     */
    public void setStaggeredGridLayout(int spanCount) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    public void setStateView(int size) {
        if (size > 0) {
            if (stateView.getVisibility() == VISIBLE) {
                stateView.setVisibility(GONE);
            }
        } else {
            if (stateView.getVisibility() == GONE) {
                stateView.setVisibility(VISIBLE);
            }
        }
    }

    public void setPullLoadMoreCompleted() {
        mSpringView.onFinishFreshAndLoad();
        isLoadMore = false;
        isRefresh = false;
        mSpringView.setEnable(true);
    }

    @Override
    public void onRefresh() {
        mSpringView.setEnable(false);

        if (EasyApplication.isNetworkAvailable(mContext)) {
            isRefresh = true;
            callBack.onRefresh();
        } else {
            Toast.makeText(mContext, "网络没有连接", Toast.LENGTH_SHORT).show();
            setPullLoadMoreCompleted();
            mSpringView.setEnableFooter(false);
        }
    }

    @Override
    public void onLoadmore() {
        mSpringView.setEnable(false);

        if (EasyApplication.isNetworkAvailable(mContext)) {
            isLoadMore = true;
            callBack.onLoadMore();
        } else {
            Toast.makeText(mContext, "网络没有连接", Toast.LENGTH_SHORT).show();
            setPullLoadMoreCompleted();
            mSpringView.setEnableFooter(false);
        }
    }

    class TouchRecyclerView implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return isRefresh || isLoadMore;
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    public void setNestedScrollingEnabled(boolean isScroll){
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setHasMore(int size, int count) {
        setPullLoadMoreCompleted();
        if (size == count) {
            mSpringView.setEnableFooter(true);
        } else {
            mSpringView.setEnableFooter(false);
        }
    }

    public interface PullRecyclerViewCallBack {
        void onRefresh();

        void onLoadMore();
    }

}
