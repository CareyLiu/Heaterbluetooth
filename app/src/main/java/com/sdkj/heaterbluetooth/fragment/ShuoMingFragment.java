package com.sdkj.heaterbluetooth.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.basicmvp.BaseFragment;
import com.sdkj.heaterbluetooth.util.Y;

import butterknife.BindView;
import butterknife.OnClick;

public class ShuoMingFragment extends BaseFragment {
    @BindView(R.id.tv_fengnuan)
    TextView tvFengnuan;
    @BindView(R.id.vv_fengnuan)
    View vvFengnuan;
    @BindView(R.id.rl_fengnuan)
    RelativeLayout rlFengnuan;
    @BindView(R.id.tv_dashui)
    TextView tvDashui;
    @BindView(R.id.vv_dashui)
    View vvDashui;
    @BindView(R.id.rl_dashui)
    RelativeLayout rlDashui;
    @BindView(R.id.iv_fengnuan)
    ImageView ivFengnuan;
    @BindView(R.id.iv_dashui)
    ImageView ivDashui;

    @Override
    protected void initLogic() {
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_shuoming;
    }

    @Override
    protected void initView(View rootView) {

    }

    public static ShuoMingFragment newInstance() {
        Bundle args = new Bundle();
        ShuoMingFragment fragment = new ShuoMingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.rl_fengnuan, R.id.rl_dashui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_fengnuan:
                selectFengnuan();
                break;
            case R.id.rl_dashui:
                selectDashui();
                break;
        }
    }

    private void selectDashui() {
        tvFengnuan.setTextColor(Y.getColor(R.color.white));
        tvDashui.setTextColor(Y.getColor(R.color.text_color_blue));
        vvFengnuan.setVisibility(View.GONE);
        vvDashui.setVisibility(View.VISIBLE);
        ivFengnuan.setVisibility(View.GONE);
        ivDashui.setVisibility(View.VISIBLE);
    }

    private void selectFengnuan() {
        tvFengnuan.setTextColor(Y.getColor(R.color.text_color_blue));
        tvDashui.setTextColor(Y.getColor(R.color.white));
        vvFengnuan.setVisibility(View.VISIBLE);
        vvDashui.setVisibility(View.GONE);
        ivFengnuan.setVisibility(View.VISIBLE);
        ivDashui.setVisibility(View.GONE);
    }
}
