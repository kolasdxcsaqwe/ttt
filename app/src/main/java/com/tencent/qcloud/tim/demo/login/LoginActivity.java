package com.tencent.qcloud.tim.demo.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.tencent.qcloud.tim.demo.databinding.ActivityLoginBinding;
import com.tencent.qcloud.tim.demo.dialog.DialogUpgradeVersionConfirm;
import com.tencent.qcloud.tim.demo.main.MainActivity;
import com.tencent.qcloud.tim.demo.main.MainMinimalistActivity;
import com.tencent.qcloud.tim.demo.utils.AppUtils;
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

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding alb;
    long currentTime=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alb= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(alb.getRoot());

        try {
            alb.version.setText(getString(R.string.mine_version)+":"+getPackageManager().getPackageInfo(getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        alb.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.h_enter, R.anim.donothing);
            }
        });
        alb.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alb.etLoginName.getText().length()==0)
                {
                    ToastUtil.toastShortMessage("请输入用户名");
                    return;
                }
                if(alb.etPassword.getText().length()==0)
                {
                    ToastUtil.toastShortMessage("请输入密码");
                    return;
                }

                login(alb.etLoginName.getText().toString(),alb.etPassword.getText().toString());
            }
        });

        getVersion();

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentTime=0;
    }

    private void login(String userName,String password)
    {
        RequestBody requestBody= new FormBody.Builder().add("userName",userName).add("loginPwd",password).build();

        HttpRequest.post(HttpRequest.login, requestBody, new OkhttpCallBack(true,this) {
            @Override
            public void onHttpFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onHttpResponse(@NonNull Call call, @NonNull JSONObject jsonObject, boolean isSuccess, String msg) throws IOException {
                LoginIMResultBean loginIMResultBean=new Gson().fromJson(jsonObject.toString(), LoginIMResultBean.class);
                if(loginIMResultBean.getCode().equals("0"))
                {
                    loginIMResultBean.setPassword(password);
                    loginIMResultBean.setUsername(userName);
                    loginIM(loginIMResultBean);
                }
                else
                {
                    ToastUtil.toastShortMessage(msg);
                    alb.rlSKV.setVisibility(View.GONE);
                }
            }
        });
    }


    private void loginIM(LoginIMResultBean bean) {
        alb.rlSKV.setVisibility(View.VISIBLE);
        TUILogin.login(DemoApplication.instance(), AppConfig.DEMO_SDK_APPID, bean.getUsername(), bean.getUsersig(), TUIUtils.getLoginConfig(), new TUICallback() {
            @Override
            public void onError(final int code, final String desc) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        alb.rlSKV.setVisibility(View.GONE);
                        ToastUtil.toastLongMessage(getString(R.string.failed_login_tip) + ", errCode = " + code + ", errInfo = " + desc);
                    }
                });
            }

            @Override
            public void onSuccess() {
                UserInfo.getInstance().setAutoLogin(true);
                SPUtils.getInstance().save(SPUtils.loginData,new Gson().toJson(bean));

                Intent intent;
                if (AppConfig.DEMO_UI_STYLE == 0) {
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(LoginActivity.this, MainMinimalistActivity.class);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.h_exit, R.anim.donothing);

                TIMAppService.getInstance().registerPushManually();

                finish();
            }
        });
    }

    private void showMainActivityAndFinish() {
//        Intent intent = new Intent();
//        intent.setClass(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        this.startActivity(intent);
//        overridePendingTransition(R.anim.h_exit, R.anim.donothing);
//        finish();
    }

    @Override
    public void onBackPressed() {

       if(System.currentTimeMillis()-currentTime<2000)
       {
           super.onBackPressed();
       }
       else
       {
           ToastUtil.toastShortMessage("再按一次退出");
           currentTime=System.currentTimeMillis();
       }

    }


    private void getVersion()
    {
        HttpRequest.get(HttpRequest.systemVersion, new OkhttpCallBack(true,this) {
            @Override
            public void onHttpFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onHttpResponse(@NonNull Call call, @NonNull JSONObject jsonObject, boolean isSuccess, String msg) throws IOException {
                if(isSuccess)
                {
                    JSONObject data=jsonObject.optJSONObject("data");
                    JSONObject android=data.optJSONObject("android");
                    String version=android.optString("version","");
                    String url=android.optString("url","");

                    String onlineCode = version.replaceAll("\\.", "");
                    String NativeCode = AppUtils.getAppVersionName(LoginActivity.this).replaceAll("\\.", "");
                    try {
                        if (Integer.valueOf(onlineCode) > Integer.valueOf(NativeCode)) {
                            DialogUpgradeVersionConfirm.showDialog(getSupportFragmentManager()).setOnClickConfirmListener(new DialogUpgradeVersionConfirm.OnClickConfirmListener() {
                                @Override
                                public void onCLick(boolean isConfirm) {
                                    if(isConfirm)
                                    {
                                        AppUtils.skipWebpage(LoginActivity.this,url);
                                    }
                                    else
                                    {
                                        finish();
                                    }
                                }
                            });
                        }
                        else
                        {
                            String loginData = SPUtils.getInstance().get(SPUtils.loginData, "");
                            if (!TextUtils.isEmpty(loginData)) {
                                LoginIMResultBean loginIMResultBean = new Gson().fromJson(loginData, LoginIMResultBean.class);
                                login(loginIMResultBean.getUsername(),loginIMResultBean.getPassword());
                            }
                            else
                            {
                                alb.rlSKV.setVisibility(View.GONE);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                else
                {
                    ToastUtil.toastShortMessage(msg);
                }
            }
        });
    }

}
