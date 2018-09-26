package com.easychange.admin.easychangemerchant.login;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bumptech.glide.Glide;
import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.adapter.GridViewAddImgesAdapter;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.base.BaseDialog;
import com.easychange.admin.easychangemerchant.bean.AllShopTypeBean;
import com.easychange.admin.easychangemerchant.bean.LoginBean;
import com.easychange.admin.easychangemerchant.http.DialogCallback;
import com.easychange.admin.easychangemerchant.http.HttpManager;
import com.easychange.admin.easychangemerchant.http.JsonCallback;
import com.easychange.admin.easychangemerchant.http.ResponseBean;
import com.easychange.admin.easychangemerchant.utils.MyUtils;
import com.easychange.admin.easychangemerchant.utils.SendSmsTimerUtils;
import com.easychange.admin.easychangemerchant.views.MyGridView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
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
import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegistActivity extends BaseActivity implements LoginPresenter.TokenCallBack, AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {


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
    @BindView(R.id.iv_shop_head)
    ImageView iv_shop_head;
    @BindView(R.id.et_jianjie)
    EditText et_jianjie;
    @BindView(R.id.mygridview)
    MyGridView gridview;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String businesLicenseImg;
    private OptionsPickerView pvCustomOptions;
    private List<String> timeList;
    private List<String> typeList = new ArrayList<>();
    private LoginPresenter presenter;
    private int date;
    private MapView mMapView;
    private AMap aMap;
    private GridViewAddImgesAdapter addImgesAdpter;
    private List<LocalMedia> dianlist = new ArrayList<>();
    private int currPhotoType;
    private String shopHeadImg;
    private String city;
    private double longitude;
    private double latitude;
    private int photoCount;
    private StringBuilder stringBuilder = new StringBuilder();
    private String headPic;
    private String zhizhaoPic;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private GeocodeSearch geocoderSearch;
    private String[] permissions = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.mapview);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        //初始化地图控制器对象
        aMap = mMapView.getMap();
        initData();
        presenter = new LoginPresenter(this, this);
        initDialog();
        initLocation();
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(RegistActivity.this, MyMapActivity.class);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
                startActivityForResult(intent, 0);
            }
        });
        for (int i = 0; i < permissions.length; i++) {
            checkPermission(permissions[i]);
        }
    }

    private void checkPermission(String permission) {
        //使用兼容库就无需判断系统版本
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(getApplication(),
                permission);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //拥有权限，执行操作
        } else {
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(this, new String[]{permission}, 1001);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //通过requestCode来识别是否同一个请求
        if (requestCode == 1001) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意，执行操作
            } else {
                //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(this, "应用需要此权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initLocation() {
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                latitude=amapLocation.getLatitude();//获取纬度
                longitude=amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                city = amapLocation.getCity();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        //逆地理编码--坐标转地址
        String city = regeocodeResult.getRegeocodeAddress().getCity();
        if (!this.city.equals(city)) {
            this.city = city;
        }
        etCompanyAddress.setText(regeocodeResult.getRegeocodeAddress().getCity()
                + regeocodeResult.getRegeocodeAddress().getDistrict()
                + regeocodeResult.getRegeocodeAddress().getTownship()
                + regeocodeResult.getRegeocodeAddress().getBuilding());
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        //解析result获取坐标信息---地理编码--地址转坐标
        LatLonPoint latLonPoint =
                geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint();
        city = geocodeResult.getGeocodeAddressList().get(0).getCity();
        latitude = latLonPoint.getLatitude();
        longitude = latLonPoint.getLongitude();
    }

    private void initAddress() {
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
        GeocodeQuery query = new GeocodeQuery(city, etCompanyAddress.getText().toString());
        geocoderSearch.getFromLocationNameAsyn(query);
    }

    private void initDialog() {
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
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
        /**
         * 添加照片adapter
         */
        addImgesAdpter = new GridViewAddImgesAdapter(dianlist, this);
        gridview.setAdapter(addImgesAdpter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currPhotoType = 1;
                showCameraDialog(8);
            }
        });
    }


    @OnClick({R.id.tv_verify, R.id.iv_camera, R.id.tv_commit, R.id.iv_back, R.id.iv_select_date, R.id.iv_select_type, R.id.iv_shop_head})
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
                currPhotoType = 2;
                showCameraDialog(1);
                break;
            case R.id.iv_shop_head:
                currPhotoType = 0;
                showCameraDialog(1);
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
                photoCount = 0;
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
        initAddress();
        if (TextUtils.isEmpty(zhizhaoPic)) {
            Toast.makeText(RegistActivity.this, "请选择营业执照", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(headPic)) {
            Toast.makeText(RegistActivity.this, "请选择商家头像", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_jianjie.getText().toString())) {
            Toast.makeText(RegistActivity.this, "请输入商家简介", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dianlist.size() == 0) {
            Toast.makeText(RegistActivity.this, "请选择门店图片", Toast.LENGTH_SHORT).show();
            return;
        }
        imageUpload1(headPic);
    }

    private void pushData() {
        String text = etCompanyName.getText().toString().trim();
        String code = etVerify.getText().toString().trim();
        String fdname = etFarenName.getText().toString().trim();
        String phone = etFarenPhone.getText().toString().trim();
        String address = etCompanyAddress.getText().toString().trim();
        presenter.getRegistInfo(text, fdname, phone, code, tvShopType.getText().toString(), address,
                etShopPhone.getText().toString(), Integer.parseInt(etYingyee.getText().toString()),
                Integer.parseInt(etFaxingliang.getText().toString()), date, businesLicenseImg,
                et_jianjie.getText().toString(), shopHeadImg, stringBuilder.toString(), city, longitude, latitude);
    }

    @Override
    public void getPassLoginInfo(LoginBean loginBean) {

    }

    @Override
    public void getRegistCode(ResponseBean responseBean) {
        if (responseBean.code == 200) {
            Toast.makeText(mContext, responseBean.msg, Toast.LENGTH_SHORT).show();
            SendSmsTimerUtils.sendSms(tvVerify, R.color.colorPrimary, R.color.colorPrimary);
        } else {
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

    private void showCameraDialog(final int maxNum) {
        mDialog.show();
        mDialog.getView(R.id.photo_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                requestPhoto(maxNum);
            }
        });
    }

    private void requestPhoto(int maxNum) {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(maxNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(maxNum == 1 ? PictureConfig.SINGLE : PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.SINGLE
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
                    if (selectList.size() == 0) {
                        return;
                    }
                    String cutPath = selectList.get(0).getCutPath();
                    if (currPhotoType == 0) {
                        Glide.with(this).load(cutPath).into(iv_shop_head);
                        headPic = cutPath;
                    } else if (currPhotoType == 1) {
                        dianlist.addAll(selectList);
                        addImgesAdpter.setList(dianlist);
                        addImgesAdpter.notifyDataSetChanged();
                        selectList.clear();
                    } else if (currPhotoType == 2) {
                        Glide.with(this).load(cutPath).into(ivCamera);
                        zhizhaoPic = cutPath;
                    }
                    break;
            }
        }
        if (resultCode == 1001) {
            latitude = data.getDoubleExtra("latitude", 0);
            longitude = data.getDoubleExtra("longitude", 0);
            geocoderSearch = new GeocodeSearch(this);
            geocoderSearch.setOnGeocodeSearchListener(this);
            // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latitude, longitude), Integer.MAX_VALUE, GeocodeSearch.AMAP);
            geocoderSearch.getFromLocationAsyn(query);
        }
    }

    private void imageUpload1(String cutPath) {
        //压缩一下再上传，不然拍照基本都四五兆一张图片，上传太耗时间，而且服务器也有限制，不接受3M以上的图片
        new Compressor(this)
                .compressToFileAsFlowable(new File(cutPath))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) {
                        OkGo.<String>post("http://39.106.220.142/resident/upload")
                                .tag(this)
                                .params("images", file)
                                .execute(new DialogCallback<String>(RegistActivity.this) {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        String body = response.body();
                                        Log.d("TAG", "图片成功：" + body);
                                        JSONObject jsonobj = null;
                                        try {
                                            jsonobj = new JSONObject(body);
                                            JSONArray data = jsonobj.getJSONArray("data");
                                            shopHeadImg = data.getString(0);
                                            imageUpload3(zhizhaoPic);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();

                    }
                });
    }

    private void imageUpload2(String cutPath) {
        new Compressor(this)
                .compressToFileAsFlowable(new File(cutPath))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) {
                        OkGo.<String>post("http://39.106.220.142/resident/upload")
                                .tag(this)
                                .params("images", file)
                                .execute(new DialogCallback<String>(RegistActivity.this) {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        String body = response.body();
                                        Log.d("TAG", "图片成功：" + body);
                                        JSONObject jsonobj = null;
                                        try {
                                            jsonobj = new JSONObject(body);
                                            JSONArray data = jsonobj.getJSONArray("data");
                                            photoCount++;
                                            stringBuilder.append(data.getString(0) + ",");
                                            if (photoCount == dianlist.size()) {
                                                pushData();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();

                    }
                });
    }

    private void imageUpload3(String cutPath) {
        new Compressor(this)
                .compressToFileAsFlowable(new File(cutPath))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) {
                        OkGo.<String>post("http://39.106.220.142/resident/upload")
                                .tag(this)
                                .params("images", file)
                                .execute(new DialogCallback<String>(RegistActivity.this) {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        String body = response.body();
                                        Log.d("TAG", "图片成功：" + body);
                                        JSONObject jsonobj = null;
                                        try {
                                            jsonobj = new JSONObject(body);
                                            JSONArray data = jsonobj.getJSONArray("data");
                                            businesLicenseImg = data.getString(0);
                                            for (int i = 0; i < dianlist.size(); i++) {
                                                imageUpload2(dianlist.get(i).getCutPath());
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();

                    }
                });
    }

}
