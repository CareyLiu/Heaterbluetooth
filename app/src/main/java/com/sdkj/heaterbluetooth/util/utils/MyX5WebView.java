package com.sdkj.heaterbluetooth.util.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


public class MyX5WebView extends WebView {
    public String title;

    private float startx;
    private float starty;
    private float offsetx;
    private float offsety;

    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
          title = webView.getTitle();

        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public MyX5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.setWebViewClient(client);
        // this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        this.addJavascriptInterface(new AndroidForJs(arg0), "JavaScriptInterface");
        initWebViewSettings();
      this.getView().setClickable(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                startx = event.getX();
                starty = event.getY();
                Log.e("MotionEvent", "webview按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("MotionEvent", "webview滑动");
                offsetx = Math.abs(event.getX() - startx);
                offsety = Math.abs(event.getY() - starty);
                if (offsetx > offsety) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    Log.e("MotionEvent", "屏蔽了父控件");
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    Log.e("MotionEvent", "事件传递给父控件");
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);

        /* 设置为true表示支持使用js打开新的窗口 */
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //启用或禁止WebView访问文件数据
        webSetting.setAllowFileAccess(true);
        //设置布局方式

/**
 * 1.NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度

 2.NORMAL：正常显示不做任何渲染

 3.SINGLE_COLUMN：把所有内容放大webview等宽的一列中
 */
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
//设置是否支持变焦
        webSetting.setSupportZoom(true);
        /* 设置是否允许webview使用缩放的功能,我这里设为false,不允许 */
        webSetting.setBuiltInZoomControls(false);
//		 /* 设置为使用webview推荐的窗口 */

        /**
         * 添加这个属性-- 会出现大空白页面
         */
//        webSetting.setUseWideViewPort(true);

        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
           /* 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
        webSetting.setDomStorageEnabled(true);
        /* HTML5的地理位置服务,设置为true,启用地理定位 */
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        /* 提高网页渲染的优先级 */
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //设置缓存模式
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

//	@Override
//	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
//		boolean ret = super.drawChild(canvas, child, drawingTime);
//		canvas.save();
//		Paint paint = new Paint();
//		paint.setColor(0x7fff0000);
//		paint.setTextSize(24.f);
//		paint.setAntiAlias(true);
//		if (getX5WebViewExtension() != null) {
//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
//					+ android.os.Process.myPid(), 10, 50, paint);
//			canvas.drawText(
//					"X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
//					100, paint);
//		} else {
//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
//					+ android.os.Process.myPid(), 10, 50, paint);
//			canvas.drawText("Sys Core", 10, 100, paint);
//		}
//		canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
//		canvas.drawText(Build.MODEL, 10, 200, paint);
//		canvas.restore();
//		return ret;
//	}

    public MyX5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
    }

}
