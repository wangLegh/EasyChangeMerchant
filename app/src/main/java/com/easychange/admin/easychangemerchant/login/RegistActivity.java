package com.easychange.admin.easychangemerchant.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bumptech.glide.Glide;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.base.BaseDialog;
import com.easychange.admin.easychangemerchant.bean.AllShopTypeBean;
import com.easychange.admin.easychangemerchant.bean.LoginBean;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.JsonCallback;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.easychange.admin.easychangemerchant.utils.MyUtils;
import com.easychange.admin.easychangemerchant.utils.SendSmsTimerUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity implements LoginPresenter.TokenCallBack {


    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.et_faren_name)
    EditText etFarenName;
    @BindView(R.id.et_faren_phone)
    EditText etFarenPhone;
    @BindView(R.id.view_01)
    TextView view01;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.tv_shop_type)
    TextView tvShopType;
    @BindView(R.id.iv_select_type)
    ImageView ivSelectType;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_shop_phone)
    EditText etShopPhone;
    @BindView(R.id.et_yingyee)
    EditText etYingyee;
    @BindView(R.id.et_faxingliang)
    EditText etFaxingliang;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_select_date)
    ImageView ivSelectDate;
    @BindView(R.id.et_company_address)
    EditText etCompanyAddress;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private String businesLicenseImg;
    private File file;
    private OptionsPickerView pvCustomOptions;
    private List<String> timeList;
    private List<String> typeList = new ArrayList<>();
    private LoginPresenter presenter;
    private int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        initData();
        presenter = new LoginPresenter(this, this);
    }

    private void initData() {
        timeList = new ArrayList<>();
        timeList.add("1年");
        timeList.add("2年");
        timeList.add("3年");
        timeList.add("4年");
        timeList.add("5年");
        timeList.add("6年");
        timeList.add("7年");
        timeList.add("8年");
        initCustomOptionPicker(true, timeList);

        new HttpManager<ResponseBean<List<AllShopTypeBean>>>("shop/selectAllCategories", this)
                .getRequets(new JsonCallback<ResponseBean<List<AllShopTypeBean>>>() {
                    @Override
                    public void onSuccess(Response<ResponseBean<List<AllShopTypeBean>>> response) {
                        if (response.body().data != null) {
                            if (response.body().code == 200) {
                                List<AllShopTypeBean> data = response.body().data;
                                if (data != null && data.size() > 0) {
                                    for (int i = 0; i < data.size(); i++) {
                                        typeList.add(data.get(i).getTitle());
                                    }
                                }
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.tv_verify, R.id.iv_camera, R.id.tv_commit, R.id.iv_back, R.id.iv_select_date, R.id.iv_select_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verify:
                if (TextUtils.isEmpty(etFarenPhone.getText().toString())) {
                    Toast.makeText(RegistActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!MyUtils.isMobileNO(etFarenPhone.getText().toString())) {
                    Toast.makeText(RegistActivity.this, "请输入正确格式的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.getRegistCode(etFarenPhone.getText().toString());
                break;
            case R.id.iv_camera:
                showCameraDialog();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_select_date:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                initCustomOptionPicker(true, timeList);
                pvCustomOptions.show();
                break;
            case R.id.iv_select_type:
                initCustomOptionPicker(false, typeList);
                pvCustomOptions.show();
                break;
            case R.id.tv_commit:
                commit();
                break;
        }
    }

    private void commit() {
        String text = etCompanyName.getText().toString().trim();
        String code = etVerify.getText().toString().trim();
        String fdname = etFarenName.getText().toString().trim();
        String phone = etFarenPhone.getText().toString().trim();
        String address = etCompanyAddress.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(RegistActivity.this, "请输入商家名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fdname)) {
            Toast.makeText(RegistActivity.this, "请输入法人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(RegistActivity.this, "请输入法人手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(RegistActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(tvShopType.getText().toString())) {
            Toast.makeText(RegistActivity.this, "请选择商家类别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etShopPhone.getText().toString())) {
            Toast.makeText(RegistActivity.this, "请输入商家电话", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etYingyee.getText().toString())) {
            Toast.makeText(RegistActivity.this, "请输入您近半年月度平均营业额", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etFaxingliang.getText().toString())) {
            Toast.makeText(RegistActivity.this, "请输入发行量", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(tvDate.getText().toString())) {
            Toast.makeText(RegistActivity.this, "请选择入驻期限", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(RegistActivity.this, "请输入商家地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(businesLicenseImg)) {
            Toast.makeText(RegistActivity.this, "请选择营业执照", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.getRegistInfo(text, fdname, phone, code, tvShopType.getText().toString(), address,
                etShopPhone.getText().toString(), Integer.parseInt(etYingyee.getText().toString()),
                Integer.parseInt(etFaxingliang.getText().toString()), date, businesLicenseImg);
    }

    @Override
    public void getPassLoginInfo(LoginBean loginBean) {

    }

    @Override
    public void getRegistCode(ResponseBean responseBean) {
        if (responseBean.code==200){
            Toast.makeText(mContext, responseBean.msg, Toast.LENGTH_SHORT).show();
            SendSmsTimerUtils.sendSms(tvVerify, R.color.colorPrimary, R.color.colorPrimary);
        }else {
            Toast.makeText(mContext, responseBean.msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registShop(ResponseBean responseBean) {
        finish();
    }

    @Override
    public void getForgetPswCode(ResponseBean responseBean) {

    }

    @Override
    public void getForgetPassInfo(ResponseBean responseBean) {

    }


    private void initCustomOptionPicker(final boolean isdate, final List<String> data) {
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (isdate) {
                    date = options1 + 1;
                    tvDate.setText(data.get(options1));
                } else {
                    tvShopType.setText(data.get(options1));
                }
                pvCustomOptions.dismiss();
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
                        if (!isdate) {
                            tv_title.setText("选择商家类别");
                        }
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

    private void showCameraDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_secltor_carmea)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.Bottom_Top_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.camera_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                requestCamera();
            }
        });
        mDialog.getView(R.id.photo_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                requestPhoto();
            }
        });
    }

    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                //                .selectionMedia(list)// 是否传入已选图片
                //                        .videoMaxSecond(15)
                //                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    private void requestCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                //                .selectionMedia(list)// 是否传入已选图片
                //                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    cutPath = selectList.get(0).getCutPath();
                    Glide.with(this).load(cutPath).into(ivCamera);
                    file = new File(cutPath);
                    imageUpload();
                    break;
            }
        }
    }

    private void imageUpload() {
        OkGo.<String>post("http://39.106.220.142/resident/upload")
                .tag(this)
                .params("images", file)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Log.d("TAG", "图片成功：" + body);
                        JSONObject jsonobj = null;
                        try {
                            jsonobj = new JSONObject(body);
                            JSONArray data = jsonobj.getJSONArray("data");
                            businesLicenseImg = data.getString(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


}
