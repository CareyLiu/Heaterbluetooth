package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.util.utils.MyX5WebView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TuanYouWebView extends BaseActivity {
    @BindView(R.id.webview)
    MyX5WebView webview;
    @BindView(R.id.iv_image_back)
    ImageView ivImageBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.ll_fanhuishagnyibu)
    LinearLayout llFanhuishagnyibu;
    @BindView(R.id.ll_guanbi)
    LinearLayout llGuanbi;

    @Override
    public int getContentViewResId() {
        return R.layout.jd_web;

        //重写此方法，查看浏览器内部跳转
        // Log.i(String.valueOf(DetailsActivity.this), urlStr);
        /*|| url.startsWith("http:") || url.startsWith("https:")*/
        //类型我目前用到的是微信、支付宝、拨号 三种跳转方式，其他类型自加

    }

    WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url == null) {
                return false;
            }
            Log.e("url=", url);
            try {
                /*|| url.startsWith("http:") || url.startsWith("https:")*/
                if (url.startsWith("weixin://") || url.contains("alipays://platformapi")) {//如果微信或者支付宝，跳转到相应的app界面,
                    webview.goBack();
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(TuanYouWebView.this, "未安装相应的客户端", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }


                /**
                 *
                 * 设置 Header 头方法
                 * window.czb.extraHeaders(String key, String value)
                 */
                if (customNavigationActivity != null && customNavigationActivity.getKey() != null) {
                    Map extraHeaders = new HashMap();
                    extraHeaders.put(customNavigationActivity.getKey(), customNavigationActivity.getValue());
                    webview.loadUrl(url, extraHeaders);
                } else {
                    webview.loadUrl(url);
                }
                return true;
            } catch (Exception e) {
                return false;

            }

        }
    };


//    @Override
//    public void initImmersion() {
//        super.initImmersion();
//        mImmersionBar.titleBar(R.id.title_header).init();
//    }

    CustomNavigationJsObject1 customNavigationActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String strWeb = getIntent().getStringExtra("tuanYou");
        webview.loadUrl(strWeb);
        customNavigationActivity = new CustomNavigationJsObject1(this);
        webview.addJavascriptInterface(customNavigationActivity, "czb");//第二个参数czb不可更改，

        webview.setWebViewClient(client);

        llGuanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llFanhuishagnyibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    // 返回上一页面
                    //   webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webview.goBack();
                }
            }
        });
//        tvBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (webview.canGoBack()) {
//                    // 返回上一页面
//                    //   webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//                    webview.goBack();
//                }
//            }
//        });

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String tuanYouUrl) {
        Intent intent = new Intent(context, TuanYouWebView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("tuanYou", tuanYouUrl);
        context.startActivity(intent);
    }

    /* 改写物理按键返回的逻辑 */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            // 返回上一页面
            //   webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.destroy();
    }
}

