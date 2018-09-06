package com.easychange.admin.easychangemerchant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.YihuanbiAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.YihuanbiBean;
import com.easychange.admin.easychangemerchant.p.YihuanbiPresenter;
import com.easychange.admin.easychangemerchant.views.WanRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YihuanbiActivity extends BaseActivity implements YihuanbiAdapter.OnItemClickListener, WanRecyclerView.PullRecyclerViewCallBack, YihuanbiPresenter.YihuanbiCallBack {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.tv_faxing_num)
    TextView tvFaxingNum;
    @BindView(R.id.tv_shengyu_num)
    TextView tvShengyuNum;
    @BindView(R.id.tv_shenqing)
    TextView tvShenqing;
    @BindView(R.id.yihuanbi_recyclerView)
    WanRecyclerView yihuanbiRecyclerView;
    private YihuanbiAdapter adapter;
    private YihuanbiPresenter presenter;
    int pageNum = 1;
    private List<YihuanbiBean.ListBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yihuanbi);
        ButterKnife.bind(this);
        presenter = new YihuanbiPresenter(this, this);
        presenter.YihuanbiRequest( pageNum, 10);

        initView();
    }

    private void initView() {
        viewHeaderTitle.setText("易换券");
        yihuanbiRecyclerView.setLinearLayout();
        adapter = new YihuanbiAdapter(mData, this);
        yihuanbiRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        yihuanbiRecyclerView.setPullRecyclerViewListener(this);

    }

    @OnClick({R.id.view_header_back, R.id.tv_shenqing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.tv_shenqing:
                Intent intent = new Intent(mContext, ShenqingActivity.class);
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==1){
            pageNum = 1;
            mData.clear();
            presenter.YihuanbiRequest( pageNum, 10);
        }
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onRefresh() {
        pageNum = 1;
        mData.clear();
        presenter.YihuanbiRequest( pageNum, 10);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        presenter.YihuanbiRequest( pageNum, 10);
    }

    @Override
    public void requestYihuanbiSuccess(YihuanbiBean datas) {
        if (datas != null) {
            tvFaxingNum.setText(datas.getCirculation() + "");
            tvShengyuNum.setText(datas.getResidueCurrency() + "");
            mData.addAll(datas.getList());
            yihuanbiRecyclerView.setHasMore(datas.getList().size(), 10);
        } else {
            yihuanbiRecyclerView.setHasMore(0, 10);
        }
        adapter.notifyDataSetChanged();
        yihuanbiRecyclerView.setStateView(mData.size());
    }

    @Override
    public void requestFail(String message) {
        yihuanbiRecyclerView.setHasMore(0, 10);
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestApplySuccess() {

    }


}
