package com.tencent.qcloud.tuikit.tuicontact.classicui.pages;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMUserFullInfo;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.qcloud.tuikit.timcommon.component.gatherimage.ShadeImageView;
import com.tencent.qcloud.tuikit.timcommon.component.impl.GlideEngine;
import com.tencent.qcloud.tuikit.tuicontact.R;
import com.tencent.qcloud.tuikit.tuicontact.util.QrcodeGen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyQrCodeDialog extends Dialog {

    public MyQrCodeDialog(@NonNull Context context) {
        super(context, R.style.TranslucentDialogTheme);
        initView();
    }

    public MyQrCodeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    protected MyQrCodeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView()
    {
        setContentView(R.layout.dialog_myqrcode);

        ShadeImageView self_icon=findViewById(R.id.myicon);

        TextView tv_name=findViewById(R.id.tv_name);

        ImageView ivQrcode=findViewById(R.id.ivQrcode);



        String selfUserID = V2TIMManager.getInstance().getLoginUser();
        tv_name.setText(selfUserID);

        String path= QrcodeGen.geInstance().getPersonalQrcode(getContext(),selfUserID);
        if(!TextUtils.isEmpty(path))
        {
            Bitmap bitmap= BitmapFactory.decodeFile(path);
            ivQrcode.setImageBitmap(bitmap);
        }

        List<String> selfIdList = new ArrayList<>();
        selfIdList.add(selfUserID);
        V2TIMManager.getInstance().getUsersInfo(selfIdList, new V2TIMValueCallback<List<V2TIMUserFullInfo>>() {
            @Override
            public void onSuccess(List<V2TIMUserFullInfo> v2TIMUserFullInfos) {
                V2TIMUserFullInfo info=v2TIMUserFullInfos.get(0);
                String mIconUrl = info.getFaceUrl();
                GlideEngine.loadUserIcon(self_icon, mIconUrl, 10);
            }

            @Override
            public void onError(int code, String desc) {}
        });
    }

}