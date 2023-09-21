package com.tencent.qcloud.tim.demo.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.tencent.qcloud.tim.demo.BaseActivity;
import com.tencent.qcloud.tim.demo.DemoApplication;
import com.tencent.qcloud.tim.demo.R;
import com.tencent.qcloud.tim.demo.TIMAppService;
import com.tencent.qcloud.tim.demo.bean.LoginIMResultBean;
import com.tencent.qcloud.tim.demo.bean.UserInfo;
import com.tencent.qcloud.tim.demo.config.AppConfig;
import com.tencent.qcloud.tim.demo.databinding.ActivityRegisterBinding;
import com.tencent.qcloud.tim.demo.main.MainActivity;
import com.tencent.qcloud.tim.demo.main.MainMinimalistActivity;
import com.tencent.qcloud.tim.demo.utils.DemoLog;
import com.tencent.qcloud.tim.demo.utils.HttpRequest;
import com.tencent.qcloud.tim.demo.utils.OkhttpCallBack;
import com.tencent.qcloud.tim.demo.utils.SPUtils;
import com.tencent.qcloud.tim.demo.utils.TUIUtils;
import com.tencent.qcloud.tuicore.TUILogin;
import com.tencent.qcloud.tuicore.interfaces.TUICallback;
import com.tencent.qcloud.tuicore.util.ToastUtil;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity {

    ActivityRegisterBinding arb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arb= ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(arb.getRoot());
        arb.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arb.etLoginName.getText().length()==0)
                {
                    ToastUtil.toastShortMessage("请输入用户名");
                    return;
                }
                if(arb.etPassword.getText().length()==0)
                {
                    ToastUtil.toastShortMessage("请输入密码");
                    return;
                }

                register(arb.etLoginName.getText().toString(),arb.etPassword.getText().toString());
            }
        });

        arb.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
//                overridePendingTransition(R.anim.h_enter, R.anim.donothing);
            }
        });

    }
//
    private void register(String userName,String password)
    {
        RequestBody requestBody= new FormBody.Builder().add("userName",userName).add("loginPwd",password).build();

        HttpRequest.post(HttpRequest.register, requestBody, new OkhttpCallBack(true,this) {
            @Override
            public void onHttpFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onHttpResponse(@NonNull Call call, @NonNull JSONObject jsonObject, boolean isSuccess, String msg) throws IOException {

                LoginIMResultBean loginIMResultBean=new Gson().fromJson(jsonObject.toString(),LoginIMResultBean.class);
                if(loginIMResultBean.getCode().equals("0"))
                {
                    loginIMResultBean.setPassword(password);
                    loginIMResultBean.setUsername(userName);
                    loginIM(loginIMResultBean);
                }
                else
                {
                    ToastUtil.toastShortMessage(msg);
                }
            }
        });
    }


    private void loginIM(LoginIMResultBean loginIMResultBean) {

        TUILogin.login(DemoApplication.instance(), AppConfig.DEMO_SDK_APPID, loginIMResultBean.getUsername(), loginIMResultBean.getUsersig(), TUIUtils.getLoginConfig(), new TUICallback() {
            @Override
            public void onError(final int code, final String desc) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        ToastUtil.toastLongMessage(getString(R.string.failed_login_tip) + ", errCode = " + code + ", errInfo = " + desc);
                    }
                });
            }

            @Override
            public void onSuccess() {
                UserInfo.getInstance().setAutoLogin(true);

                SPUtils.getInstance().save(SPUtils.loginData,new Gson().toJson(loginIMResultBean));

                Intent intent;
                if (AppConfig.DEMO_UI_STYLE == 0) {
                    intent = new Intent(RegisterActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(RegisterActivity.this, MainMinimalistActivity.class);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.h_exit, R.anim.donothing);

                TIMAppService.getInstance().registerPushManually();

                finish();
            }
        });

    }
//
//    private void showMainActivityAndFinish() {
//        Intent intent = new Intent();
//        intent.setClass(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        this.startActivity(intent);
//        finish();
//    }

}
