package com.easychange.admin.easychangemerchant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.bean.YihuanbiBean;
import com.easychange.admin.easychangemerchant.p.YihuanbiPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenqingActivity extends BaseActivity implements YihuanbiPresenter.YihuanbiCallBack {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.et_faxingliang)
    EditText etFaxingliang;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_select_date)
    ImageView ivSelectDate;
    @BindView(R.id.tv_shenqing)
    TextView tvShenqing;
    private OptionsPickerView pvCustomOptions;
    private List<String> timeList;
    private YihuanbiPresenter presenter;
    private String substring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenqing);
        ButterKnife.bind(this);
        presenter = new YihuanbiPresenter(this, this);
        initData();
    }

    private void initData() {
        viewHeaderTitle.setText("易换券");
        timeList = new ArrayList<>();
        timeList.add("1年");
        timeList.add("2年");
        timeList.add("3年");
        timeList.add("4年");
        timeList.add("5年");
        timeList.add("6年");
        timeList.add("7年");
        timeList.add("8年");
        initCustomOptionPicker(timeList);
    }

    @OnClick({R.id.view_header_back, R.id.iv_select_date, R.id.tv_shenqing, R.id.tv_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.iv_select_date:
                pvCustomOptions.show();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                break;
            case R.id.tv_date:
                pvCustomOptions.show();
                InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                break;
            case R.id.tv_shenqing:
                if (TextUtils.isEmpty(etFaxingliang.getText().toString())) {
                    Toast.makeText(mContext, "请输入易换券数量", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvDate.getText().toString().length()<=0){
                    substring="1";
                }else {
                    substring = tvDate.getText().toString().substring(0, 1);
                }
                presenter.ApplyRequest(etFaxingliang.getText().toString() , substring);
                break;
        }
    }

    private void initCustomOptionPicker(final List<String> data) {
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tvDate.setText(data.get(options1));
                pvCustomOptions.dismiss();
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tv_cancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
                        final TextView tv_select_sure = (TextView) v.findViewById(R.id.tv_select_sure);
                        tv_select_sure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });
                    }
                })
                .setSelectOptions(3)//默认选中项
                .setContentTextSize(20)//设置滚轮文字大小
                .setBgColor(getResources().getColor(R.color.white))
                .setTextColorOut(getResources().getColor(R.color.text_black))
                .setDividerColor(getResources().getColor(R.color.tab_text_normal_color))
                .setTextColorCenter(getResources().getColor(R.color.text_black)) //设置选中项文字颜色
                .build();
        pvCustomOptions.setPicker(data);//添加数据
    }

    @Override
    public void requestYihuanbiSuccess(YihuanbiBean datas) {

    }

    @Override
    public void requestFail(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestApplySuccess() {
        Toast.makeText(mContext, "申请成功，待审核", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        setResult(1, intent);
        finish();
    }
}
