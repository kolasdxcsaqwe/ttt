package com.tencent.qcloud.tim.demo.utils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpRequest {

    private static final String HTTPS = "https";
    static final String baseUrl="http://im.juhai.xyz/im-qtapi-test/";

    public static final String register="user/register/v2";
    public static final String login="user/login/V2";
    public static final String logout="user/logout";
    public static final String updatePwd="user/updatePwd";
    public static final String systemConfig="system/config";
    public static final String systemVersion="system/version";

    static OkHttpClient okHttpClient;


    public static void get(String url, Callback callback)
    {

        Request request=new Request.Builder().addHeader("content-type","application/json")
                .url(baseUrl+url).build();

        getOkHttpClient().newCall(request).enqueue(callback);
    }

    public static void post(String url, RequestBody requestBody, Callback callback)
    {

        Request request=new Request.Builder().addHeader("content-type","application/json")
                .url(baseUrl+url).post(requestBody).build();

        getOkHttpClient().newCall(request).enqueue(callback);
    }

    public static void post(String url, List<Head> heads, RequestBody requestBody, Callback callback)
    {

        if(heads!=null)
        {
            Request.Builder request=new Request.Builder();
            request.addHeader("content-type","application/json");
            for (int i = 0; i < heads.size(); i++) {
                request.addHeader(heads.get(i).getKey(),heads.get(i).getValue());
            }
            request.url(baseUrl+url).post(requestBody);
            getOkHttpClient().newCall(request.build()).enqueue(callback);
        }

    }

    private static OkHttpClient getOkHttpClient()
    {
        if(okHttpClient==null)
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (baseUrl.toLowerCase().startsWith(HTTPS)) {

                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });

                //创建管理器
                TrustAllCerts trustAllCerts = new TrustAllCerts();
                try {
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, new TrustManager[]{trustAllCerts}, new SecureRandom());

                    //为OkHttpClient设置sslSocketFactory
                    builder.sslSocketFactory(sslContext.getSocketFactory(), trustAllCerts);
                    okHttpClient = builder.build();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    public static class Head
    {
        String key;String Value;

        public Head(String key, String value) {
            this.key = key;
            Value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }
    }

    static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }

}
