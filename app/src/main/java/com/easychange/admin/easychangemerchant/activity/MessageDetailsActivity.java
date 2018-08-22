package com.easychange.admin.easychangemerchant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.bean.MessageBean;
import com.easychange.admin.easychangemerchant.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageDetailsActivity extends AppCompatActivity {

    @BindView(R.id.view_header_back)
    FrameLayout viewHeaderBack;
    @BindView(R.id.view_header_title)
    TextView viewHeaderTitle;
    @BindView(R.id.view_header_rightBtn)
    TextView viewHeaderRightBtn;
    @BindView(R.id.tv_message_title)
    TextView tvMessageTitle;
    @BindView(R.id.tv_message_time)
    TextView tvMessageTime;
    @BindView(R.id.tv_message_content)
    TextView tvMessageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        ButterKnife.bind(this);
        viewHeaderTitle.setText("消息详情");
        MessageBean data = (MessageBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            String content = data.getContent();
            long createDate = data.getCreateDate();
            String title = data.getTitle();
            tvMessageTitle.setText(title);
            tvMessageTime.setText(MyUtils.getDateTimeFromMillisecond(createDate));
            tvMessageContent.setText(content);
        }
    }

    @OnClick({R.id.view_header_back, R.id.view_header_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_header_back:
                finish();
                break;
            case R.id.view_header_title:
                break;
        }
    }
}
