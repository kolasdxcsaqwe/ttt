package com.tencent.qcloud.tim.demo.profile;

import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tencent.qcloud.tim.demo.databinding.FragmentTaskBinding;
import com.tencent.qcloud.tim.demo.utils.HttpRequest;
import com.tencent.qcloud.tim.demo.utils.OkhttpCallBack;
import com.tencent.qcloud.tim.demo.utils.PTIWebSetting;
import com.tencent.qcloud.tim.demo.utils.SPUtils;
import com.tencent.qcloud.tuicore.util.ToastUtil;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;

public class TaskFragment extends Fragment {

    String url;

    FragmentTaskBinding fragmentTaskBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTaskBinding=FragmentTaskBinding.inflate(inflater);
        return fragmentTaskBinding.getRoot();
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if(!TextUtils.isEmpty(url) && args!=null && args.getBoolean("show") && fragmentTaskBinding!=null && fragmentTaskBinding.webView!=null)
        {
            fragmentTaskBinding.webView.loadUrl(url);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PTIWebSetting.init(fragmentTaskBinding.webView);
        fragmentTaskBinding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        fragmentTaskBinding.webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }
        });
        if(!TextUtils.isEmpty(url))
        {
            fragmentTaskBinding.webView.loadUrl(url);
        }
        else
        {
            getUrl();
        }
    }

    private void getUrl()
    {
        if(getActivity()==null || getActivity().isDestroyed() || getActivity().isFinishing())
        {
            return;
        }

        HttpRequest.get(HttpRequest.systemConfig, new OkhttpCallBack(true,getActivity()) {
            @Override
            public void onHttpFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onHttpResponse(@NonNull Call call, @NonNull JSONObject jsonObject, boolean isSuccess, String msg) throws IOException {
                if(isSuccess)
                {
                    SPUtils.getInstance().save(SPUtils.ConfigData,jsonObject.optJSONObject("data").toString());
                    url=jsonObject.optJSONObject("data").optString("webDomain","");
                    fragmentTaskBinding.webView.loadUrl(url);
                }
                else
                {
                    ToastUtil.toastShortMessage(msg);
                }
            }
        });
    }


}
