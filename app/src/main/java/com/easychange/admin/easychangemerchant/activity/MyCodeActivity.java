package com.easychange.admin.easychangemerchant.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.easychangemerchant.R;
import com.easychange.admin.easychangemerchant.base.BaseActivity;
import com.easychange.admin.easychangemerchant.utils.CacheUtils;
import com.easychange.admin.easychangemerchant.utils.MyUtils;
import com.easychange.admin.easychangemerchant.utils.QRCode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCodeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final int CODE_FOR_WRITE_PERMISSION = 1002;
    private Bitmap qrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_code);
        ButterKnife.bind(this);
        int id = CacheUtils.get("id");
        qrCode = QRCode.createQRCode(id + "", MyUtils.dip2px(this, 150));
        ivCode.setImageBitmap(qrCode);
    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                checkPermission(permission);
                break;
        }
    }

    private void checkPermission(String permission) {
        //使用兼容库就无需判断系统版本
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(mContext,
                permission);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //拥有权限，执行操作
            if (saveImageToGallery(this, qrCode)) {
                Toast.makeText(this, "已保存", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(this, new String[]{permission}, CODE_FOR_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //通过requestCode来识别是否同一个请求
        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意，执行操作
                if (saveImageToGallery(this, qrCode)) {
                    Toast.makeText(this, "已保存", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast.makeText(mContext, "保存操作需要此权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //保存图片到指定目录
    public boolean saveImageToGallery(Context context, Bitmap bmp) {
        //与系统有关的默认名称分隔符。 file.separator在Linux系统上，此字段的值为 '/'；在Windows 系统上，它为 '\'。
        String galleryPath = context.getExternalFilesDir("易换云商家二维码") + File.separator + "易换云商家二维码";
        File appDir = new File(galleryPath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            // 通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
            // 把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            // 保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            return isSuccess;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
