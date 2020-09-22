package com.sdkj.heaterbluetooth.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.AppConfig;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basepage.HomeBasicActivity;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;


import java.lang.ref.WeakReference;

import rx.functions.Action1;


public class SplashActivity extends BaseActivity {

    public static final int UPDATE_OK = 2;
    public static final int OVERTIME = 1;
    protected boolean isAnimationEnd;
    private final NotLeakHandler mHandler = new NotLeakHandler(this);

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    private static class NotLeakHandler extends Handler {
        private WeakReference<SplashActivity> mWeakReference;

        private NotLeakHandler(SplashActivity reference) {
            mWeakReference = new WeakReference<>(reference);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity reference = mWeakReference.get();
            if (reference == null) { // the referenced object has been cleared
                return;
            }
            switch (msg.what) {
                case UPDATE_OK:
                    SplashOverToGo();
                    break;
                case OVERTIME:
                    SplashOverToGo();
                    break;
            }
        }

        /**
         * 进入主界面
         */
        private void SplashOverToGo() {
            SharedPreferences sharedPreferences = mWeakReference.get().getSharedPreferences("share", MODE_PRIVATE);
            boolean isFirstRun = sharedPreferences.getBoolean(AppConfig.IS_FIRST_RUN, true);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (isFirstRun) {
                editor.putBoolean(AppConfig.IS_FIRST_RUN, false);
                editor.apply();
//                mWeakReference.get().startActivity(new Intent(mWeakReference.get(), WelcomeActivity.class));
//                mWeakReference.get().finish();
                HomeBasicActivity.actionStart(mWeakReference.get());
                mWeakReference.get().finish();
            } else {
                HomeBasicActivity.actionStart(mWeakReference.get());
                mWeakReference.get().finish();
            }
        }
    }

    ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RelativeLayout logoView = findViewById(R.id.iv_welcome);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        // 动画效果时间为2秒
        alphaAnimation.setDuration(2000);
        // 设置开始动画
        logoView.startAnimation(alphaAnimation);
        // 动画监听
        alphaAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { // 动画开始时执行此方法

            }

            @Override
            public void onAnimationRepeat(Animation animation) { // 动画重复调用时执行此方法
            }

            @Override
            public void onAnimationEnd(Animation animation) { // 动画结束时执行此方法

                shenQingQuanXian();
                isAnimationEnd = true;
            }
        });
    }

    private void shenQingQuanXian() {
        new RxPermissions(SplashActivity.this).requestEach(AppConfig.BASIC_PERMISSIONS_HEARTERBLUE)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.name.equals(Manifest.permission.READ_PHONE_STATE)) {
                            if (permission.granted) {
                                mHandler.sendEmptyMessage(UPDATE_OK);
                            }
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Toast.makeText(SplashActivity.this, "该应用需要赋予访问电话的权限，不开启将无法正常工作！", Toast.LENGTH_LONG).show();
                            mHandler.sendEmptyMessage(UPDATE_OK);
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}