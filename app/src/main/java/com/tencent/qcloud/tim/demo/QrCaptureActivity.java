package com.tencent.qcloud.tim.demo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.ViewfinderView;
import com.tencent.qcloud.tim.demo.databinding.ActivityQrScannerBinding;
import com.tencent.qcloud.tuicore.util.ToastUtil;
import com.tencent.qcloud.tuikit.timcommon.component.interfaces.IUIKitCallback;
import com.tencent.qcloud.tuikit.tuicontact.TUIContactConstants;
import com.tencent.qcloud.tuikit.tuicontact.TUIContactService;
import com.tencent.qcloud.tuikit.tuicontact.bean.ContactItemBean;
import com.tencent.qcloud.tuikit.tuicontact.classicui.pages.FriendProfileActivity;
import com.tencent.qcloud.tuikit.tuicontact.interfaces.IAddMoreActivity;
import com.tencent.qcloud.tuikit.tuicontact.presenter.AddMorePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QrCaptureActivity extends BaseActivity implements
        DecoratedBarcodeView.TorchListener, IAddMoreActivity {



    ActivityQrScannerBinding aqb;
    private CaptureManager capture;

    private ViewfinderView viewfinderView;

    private AddMorePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aqb= ActivityQrScannerBinding.inflate(getLayoutInflater());
        setContentView(aqb.getRoot());

        presenter = new AddMorePresenter();
        presenter.setAddMoreActivity(this);

        viewfinderView = findViewById(R.id.zxing_viewfinder_view);
        aqb.zxingBarcodeScanner.setTorchListener(this);
        if(hasFlash())
        {
            aqb.switchFlashlight.setVisibility(View.GONE);
        }

        capture = new CaptureManager(this, aqb.zxingBarcodeScanner);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.setShowMissingCameraPermissionDialog(false);
        capture.decode();
        aqb.zxingBarcodeScanner.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                aqb.zxingBarcodeScanner.pause();
                String resultString=result.getResult().toString();
                search(resultString);
            }
        });

        changeMaskColor(null);
        changeLaserVisibility(true);

    }


    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }


    public void switchFlashlight(View view) {
        if (getString(R.string.turn_on_flashlight).equals(aqb.switchFlashlight.getText())) {
            aqb.zxingBarcodeScanner.setTorchOn();
        } else {
            aqb.zxingBarcodeScanner.setTorchOff();
        }
    }

    @Override
    public void onTorchOn() {
        aqb.switchFlashlight.setText(R.string.turn_off_flashlight);
    }

    @Override
    public void onTorchOff() {
        aqb.switchFlashlight.setText(R.string.turn_on_flashlight);
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void changeMaskColor(View view) {
        Random rnd = new Random();
        int color = Color.argb(100, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        viewfinderView.setMaskColor(color);
    }

    public void changeLaserVisibility(boolean visible) {
        viewfinderView.setLaserVisibility(visible);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void search(String account)
    {
        List<String> accountList = new ArrayList<>();
        accountList.add(account);

        presenter.getUserInfo(account, new IUIKitCallback<ContactItemBean>() {
            @Override
            public void onSuccess(ContactItemBean data) {
                Intent intent = new Intent(TUIContactService.getAppContext(), FriendProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(TUIContactConstants.ProfileType.CONTENT, data);
                TUIContactService.getAppContext().startActivity(intent);
                finish();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.toastShortMessage("搜索不到该用户");
                finish();
            }
        });

    }
}
