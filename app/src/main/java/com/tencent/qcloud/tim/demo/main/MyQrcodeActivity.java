package com.tencent.qcloud.tim.demo.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMUserFullInfo;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.qcloud.tim.demo.BaseActivity;
import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tim.demo.databinding.ActivityMyqrcodeBinding;
import com.tencent.qcloud.tim.demo.utils.QrcodeGen;
import com.tencent.qcloud.tuikit.timcommon.component.activities.BaseLightActivity;
import com.tencent.qcloud.tuikit.timcommon.component.impl.GlideEngine;
import com.tencent.qcloud.tuikit.timcommon.component.interfaces.ITitleBarLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyQrcodeActivity extends BaseLightActivity {

    ActivityMyqrcodeBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyqrcodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.aboutTitleBar.setOnClickListener(v -> finish());
        binding.aboutTitleBar.setTitle(getString(R.string.myqrcode), ITitleBarLayout.Position.MIDDLE);

        String path=QrcodeGen.geInstance().getPersonalQrcode(this);
        if(!TextUtils.isEmpty(path))
        {
            Bitmap bitmap= BitmapFactory.decodeFile(path);
            binding.ivQrcode.setImageBitmap(bitmap);
        }


        String selfUserID = V2TIMManager.getInstance().getLoginUser();

        binding.tvName.setText(selfUserID);

        List<String> selfIdList = new ArrayList<>();
        selfIdList.add(selfUserID);
        V2TIMManager.getInstance().getUsersInfo(selfIdList, new V2TIMValueCallback<List<V2TIMUserFullInfo>>() {
            @Override
            public void onSuccess(List<V2TIMUserFullInfo> v2TIMUserFullInfos) {
                V2TIMUserFullInfo info=v2TIMUserFullInfos.get(0);
                String mIconUrl = info.getFaceUrl();
                int radius = getResources().getDimensionPixelSize(R.dimen.demo_profile_face_radius);
                GlideEngine.loadUserIcon(binding.selfIcon, mIconUrl, radius);
            }

            @Override
            public void onError(int code, String desc) {}
        });
    }

}
