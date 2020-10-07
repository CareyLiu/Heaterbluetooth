package com.sdkj.heaterbluetooth.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.basicmvp.BaseFragment;

import butterknife.BindView;

public class ShuoMingFragment extends BaseFragment {
    @BindView(R.id.tv_fengnuan)
    TextView tvFengnuan;
    @BindView(R.id.tv_fengnuan_view)
    View tvFengnuanView;
    @BindView(R.id.rl_fengnuan)
    RelativeLayout rlFengnuan;
    @BindView(R.id.tv_shuinuan)
    TextView tvShuinuan;
    @BindView(R.id.tv_shuinuan_view)
    View tvShuinuanView;
    @BindView(R.id.rl_shuinuan)
    RelativeLayout rlShuinuan;
    @BindView(R.id.iv_fengnuan)
    ImageView ivFengnuan;
    @BindView(R.id.iv_shuinuan)
    ImageView ivShuinuan;
    @BindView(R.id.iv_shuomingshuo_bottom)
    ImageView ivShuomingshuoBottom;

    @Override
    protected void initLogic() {
        ivFengnuan.setVisibility(View.VISIBLE);
        tvFengnuan.setTextColor(getActivity().getResources().getColor(R.color.blue_00FFFF));
        tvFengnuanView.setBackgroundResource(R.color.blue00fff);

        tvShuinuan.setTextColor(getActivity().getResources().getColor(R.color.white));
        tvShuinuanView.setBackgroundResource(R.color.white);
        ivShuinuan.setVisibility(View.GONE);

        rlFengnuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivFengnuan.setVisibility(View.VISIBLE);
                tvFengnuan.setTextColor(getActivity().getResources().getColor(R.color.blue_00FFFF));
                tvFengnuanView.setBackgroundResource(R.color.blue00fff);

                tvShuinuan.setTextColor(getActivity().getResources().getColor(R.color.white));
                tvShuinuanView.setBackgroundResource(R.color.white);
                ivShuinuan.setVisibility(View.GONE);

            }
        });
        rlShuinuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivShuinuan.setVisibility(View.VISIBLE);
                tvFengnuan.setTextColor(getActivity().getResources().getColor(R.color.white));
                tvFengnuanView.setBackgroundResource(R.color.white);

                tvShuinuan.setTextColor(getActivity().getResources().getColor(R.color.blue_00FFFF));
                tvShuinuanView.setBackgroundResource(R.color.blue_00FFFF);
                ivFengnuan.setVisibility(View.GONE);
            }
        });
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
}
