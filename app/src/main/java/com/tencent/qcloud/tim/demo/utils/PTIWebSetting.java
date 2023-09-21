package com.tencent.qcloud.tim.demo.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PTIWebSetting {

    @SuppressLint("SetJavaScriptEnabled")
    public static void init(WebView webView){
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setCursiveFontFamily("cursive");
        webSettings.setDisplayZoomControls(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setFantasyFontFamily("fantasy");
        webSettings.setFixedFontFamily("monospace");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.NORMAL);

        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        //解决ag直播 显示不支持浏览器的问题
        webSettings.setUserAgentString("Mozilla/5.0(Linux;U;Android2.2.1;zh-cn;HTC_Wildfire_A3333Build/FRG83D)AppleWebKit/533.1(KHTML,likeGecko)Version/4.0MobileSafari/533.1/2nwf");
        //解决ag直播 显示不支持浏览器的问题
        webSettings.setSansSerifFontFamily("sans-serif");
        webSettings.setSaveFormData(true);

        webSettings.setSerifFontFamily("serif");
        webSettings.setStandardFontFamily("sans-serif");
        webSettings.setSupportMultipleWindows(false);
        webSettings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= 16) {
            webSettings.setAllowFileAccessFromFileURLs(true);
        }

        webSettings.setEnableSmoothTransition(false);

        webSettings.setGeolocationEnabled(true);

        webSettings.setSaveFormData(true);

//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.setAcceptCookie(true);
//        if (Build.VERSION.SDK_INT >= 21) {
//            cookieManager.setAcceptThirdPartyCookies(webView, true);
//            webSettings.setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
//        }
//
//        webSettings.setNavDump(false);
//        webSettings.setLightTouchEnabled(false);
//        webSettings.setSavePassword(false);
//        webSettings.setPluginsEnabled(false);
    }
}