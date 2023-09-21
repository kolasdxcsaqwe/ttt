package com.tencent.qcloud.tim.demo.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tim.demo.bean.UserInfo;
import com.tencent.qcloud.tim.demo.login.LoginActivity;
import com.tencent.qcloud.tim.demo.utils.HttpRequest;
import com.tencent.qcloud.tim.demo.utils.OkhttpCallBack;
import com.tencent.qcloud.tim.demo.utils.SPUtils;
import com.tencent.qcloud.tim.demo.utils.TUIKitConstants;
import com.tencent.qcloud.tim.demo.utils.TUIUtils;
import com.tencent.qcloud.tuicore.TUILogin;
import com.tencent.qcloud.tuicore.interfaces.TUICallback;
import com.tencent.qcloud.tuicore.util.ToastUtil;
import com.tencent.qcloud.tuikit.timcommon.component.dialog.TUIKitDialog;
import com.tencent.qcloud.tuikit.timcommon.component.fragments.BaseFragment;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;

public class ProfileFragment extends BaseFragment {
    private View mBaseView;
    private ProfileLayout mProfileLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.profile_fragment, container, false);
        initView();
        return mBaseView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mProfileLayout != null) {
            mProfileLayout.initUI();
        }
    }

    private void initView() {
        mProfileLayout = mBaseView.findViewById(R.id.profile_view);
        mBaseView.findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TUIKitDialog(getActivity())
                    .builder()
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setTitle(getString(R.string.logout_tip))
                    .setDialogWidth(0.75f)
                    .setPositiveButton(getString(com.tencent.qcloud.tuicore.R.string.sure),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               logout();
                            }
                        })
                    .setNegativeButton(getString(com.tencent.qcloud.tuicore.R.string.cancel),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {}
                        })
                    .show();
            }
        });
    }


    private void logout()
    {
        if(getActivity()==null)return;
        HttpRequest.post(HttpRequest.logout, new FormBody.Builder().build(), new OkhttpCallBack(true,getActivity()) {
            @Override
            public void onHttpFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onHttpResponse(@NonNull Call call, @NonNull JSONObject jsonObject, boolean isSuccess, String msg) throws IOException {
                if(isSuccess)
                {
                    SPUtils.getInstance().remove(SPUtils.loginData);

                    TUILogin.logout(new TUICallback() {
                        @Override
                        public void onSuccess() {
                            SPUtils.getInstance().remove(SPUtils.loginData);
                            UserInfo.getInstance().cleanUserInfo();
                            if (getActivity() != null) {
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                getActivity().finish();
                            }
                        }

                        @Override
                        public void onError(int code, String desc) {
                            ToastUtil.toastLongMessage("logout fail: " + code + "=" + desc);
                        }
                    });
                }
            }
        });


    }
}
