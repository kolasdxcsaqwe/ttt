package com.tencent.qcloud.tim.demo.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

    protected static final int REQUEST_CODE_FILE_PICKER = 51426;
    String url;

    FragmentTaskBinding fragmentTaskBinding;

    protected ValueCallback<Uri> mFileUploadCallbackFirst;
    /**
     * Android 5.0及以上版本的文件选择回调
     */
    protected ValueCallback<Uri[]> mFileUploadCallbackSecond;
    protected String mUploadableFileTypes = "image/*";

    private class CommonWebChromeClient extends WebChromeClient {

        @Override
        public void onPermissionRequest(PermissionRequest permissionRequest) {
//                super.onPermissionRequest(permissionRequest);
            permissionRequest.grant(permissionRequest.getResources());
        }

        @Override
        public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
//                super.onPermissionRequestCanceled(permissionRequest);

        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.e("Intro_Console", "Line:" + consoleMessage.lineNumber() + " Log:" + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
             setProgressBar(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //  setWebViewTitleName(title);
        }

        //  Android 2.2 (API level 8)到Android 2.3 (API level 10)版本选择文件时会触发该隐藏方法
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, null);
        }

        // Android 3.0 (API level 11)到 Android 4.0 (API level 15))版本选择文件时会触发，该方法为隐藏方法
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            openFileChooser(uploadMsg, acceptType, null);
        }

        // Android 4.1 (API level 16) -- Android 4.3 (API level 18)版本选择文件时会触发，该方法为隐藏方法
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            openFileInput(uploadMsg, null, false);
        }

        // Android 5.0 (API level 21)以上版本会触发该方法，该方法为公开方法
        @SuppressWarnings("all")
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (Build.VERSION.SDK_INT >= 21) {
                final boolean allowMultiple = fileChooserParams.getMode() == FileChooserParams.MODE_OPEN_MULTIPLE;//是否支持多选
                openFileInput(null, filePathCallback, allowMultiple);
                return true;
            } else {
                return false;
            }
        }

        @SuppressLint("NewApi")
        protected void openFileInput(final ValueCallback<Uri> fileUploadCallbackFirst, final ValueCallback<Uri[]> fileUploadCallbackSecond, final boolean allowMultiple) {
            //Android 5.0以下版本
            if (mFileUploadCallbackFirst != null) {
                mFileUploadCallbackFirst.onReceiveValue(null);
            }
            mFileUploadCallbackFirst = fileUploadCallbackFirst;

            //Android 5.0及以上版本
            if (mFileUploadCallbackSecond != null) {
                mFileUploadCallbackSecond.onReceiveValue(null);
            }
            mFileUploadCallbackSecond = fileUploadCallbackSecond;

            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);

            if (allowMultiple) {
                if (Build.VERSION.SDK_INT >= 18) {
                    i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
            }

            i.setType(mUploadableFileTypes);
            startActivityForResult(Intent.createChooser(i, "选择文件"), REQUEST_CODE_FILE_PICKER);
        }

        WebChromeClient.CustomViewCallback mCallBack;


        @Override
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            fullScreen();
            fragmentTaskBinding.webView.setVisibility(View.GONE);
            fragmentTaskBinding.flayoutRecord.setVisibility(View.VISIBLE);
            fragmentTaskBinding.flayoutRecord.addView(view);
            mCallBack = customViewCallback;
            super.onShowCustomView(view, customViewCallback);
        }

        @Override
        public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
            fullScreen();
            fragmentTaskBinding.webView.setVisibility(View.GONE);
            fragmentTaskBinding.flayoutRecord.setVisibility(View.VISIBLE);
            fragmentTaskBinding.flayoutRecord.addView(view);
            mCallBack = customViewCallback;
            super.onShowCustomView(view, i, customViewCallback);
        }

        @Override
        public void onHideCustomView() {
            fullScreen();
            if (mCallBack != null) {
                mCallBack.onCustomViewHidden();
            }
            fragmentTaskBinding.webView.setVisibility(View.VISIBLE);
            fragmentTaskBinding.flayoutRecord.removeAllViews();
            fragmentTaskBinding.flayoutRecord.setVisibility(View.GONE);
            super.onHideCustomView();
        }

//        @Override
//        public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
//            return super.onJsAlert(webView, s, s1, jsResult);
//        }
//
//        @Override
//        public boolean onJsPrompt(WebView webView, String s, String s1, String s2, JsPromptResult jsPromptResult) {
//            return super.onJsPrompt(webView, s, s1, s2, jsPromptResult);
//        }
//
//        @Override
//        public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
//            return super.onJsConfirm(webView, s, s1, jsResult);
//        }
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_FILE_PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                if (intent != null) {
                    //Android 5.0以下版本
                    if (mFileUploadCallbackFirst != null) {
                        mFileUploadCallbackFirst.onReceiveValue(intent.getData());
                        mFileUploadCallbackFirst = null;
                    } else if (mFileUploadCallbackSecond != null) {//Android 5.0及以上版本
                        Uri[] dataUris = null;

                        try {
                            if (intent.getDataString() != null) {
                                dataUris = new Uri[]{Uri.parse(intent.getDataString())};
                            } else {
                                if (Build.VERSION.SDK_INT >= 16) {
                                    if (intent.getClipData() != null) {
                                        final int numSelectedFiles = intent.getClipData().getItemCount();

                                        dataUris = new Uri[numSelectedFiles];

                                        for (int i = 0; i < numSelectedFiles; i++) {
                                            dataUris[i] = intent.getClipData().getItemAt(i).getUri();
                                        }
                                    }
                                }
                            }
                        } catch (Exception ignored) {
                        }
                        mFileUploadCallbackSecond.onReceiveValue(dataUris);
                        mFileUploadCallbackSecond = null;
                    }
                }
            } else {
                //这里mFileUploadCallbackFirst跟mFileUploadCallbackSecond在不同系统版本下分别持有了
                //WebView对象，在用户取消文件选择器的情况下，需给onReceiveValue传null返回值
                //否则WebView在未收到返回值的情况下，无法进行任何操作，文件选择器会失效
                if (mFileUploadCallbackFirst != null) {
                    mFileUploadCallbackFirst.onReceiveValue(null);
                    mFileUploadCallbackFirst = null;
                } else if (mFileUploadCallbackSecond != null) {
                    mFileUploadCallbackSecond.onReceiveValue(null);
                    mFileUploadCallbackSecond = null;
                }
            }
        }

    }

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

        fragmentTaskBinding.webView.setWebChromeClient(new CommonWebChromeClient());

        if(!TextUtils.isEmpty(url))
        {
            fragmentTaskBinding.webView.loadUrl(url);
        }
        else
        {
            getUrl();
        }

        fragmentTaskBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fragmentTaskBinding.webView.reload();
                setProgressBar(0);
                fragmentTaskBinding.swipe.setRefreshing(false);
            }
        });
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

    private void fullScreen() {
        if(getActivity()==null)
        {
            return;
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void setProgressBar(int progress)
    {
        Log.e("setProgressBar",progress+" ");
        if(fragmentTaskBinding.progressBar!=null)
        {
            if(progress==100)
            {
                fragmentTaskBinding.progressBar.setVisibility(View.GONE);
            }
            else
            {
                fragmentTaskBinding.progressBar.setVisibility(View.VISIBLE);
                fragmentTaskBinding.progressBar.setVisibility(progress);
            }
        }
    }
}
