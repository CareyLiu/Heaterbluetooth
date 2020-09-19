package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;

import butterknife.BindView;

public class SheBeiSetActivity extends BaseActivity {
    @BindView(R.id.rl_dingshi)
    RelativeLayout rlDingshi;
    @BindView(R.id.rl_jiareqicanshu)
    RelativeLayout rlJiareqicanshu;
    @BindView(R.id.rl_jiebangshebei)
    RelativeLayout rlJiebangshebei;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_guzhang)
    RelativeLayout rlGuzhang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rlDingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FengnuandishiActivity.actionStart(mContext);
            }
        });
        rlJiareqicanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JiaReQiCanShuActivity.actionStart(mContext);
            }
        });
        rlJiebangshebei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FengnuanJieActivity.actionStart(mContext);
            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlGuzhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiagnosisActivity.actionStart(mContext);
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shebei_set;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SheBeiSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();

        tv_title.setText("设置");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }
}
