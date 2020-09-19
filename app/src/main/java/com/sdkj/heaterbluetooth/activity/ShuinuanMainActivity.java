package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.sdkj.heaterbluetooth.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuinuanMainActivity extends ShuinuanBaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rl_set)
    RelativeLayout rl_set;
    @BindView(R.id.iv_shebeima)
    ImageView iv_shebeima;
    @BindView(R.id.tv_dingshi)
    TextView tv_dingshi;
    @BindView(R.id.iv_xinhao)
    ImageView iv_xinhao;
    @BindView(R.id.tv_zaixian)
    TextView tv_zaixian;
    @BindView(R.id.tv_fuwutianshushengyu)
    TextView tv_fuwutianshushengyu;
    @BindView(R.id.tv_shebei_state)
    TextView tv_shebei_state;
    @BindView(R.id.iv_shuinuan_kaijie)
    ImageView iv_shuinuan_kaijie;
    @BindView(R.id.tv_shuinuan_kaiji)
    TextView tv_shuinuan_kaiji;
    @BindView(R.id.rv_shuinuan_kaiji)
    RelativeLayout rv_shuinuan_kaiji;
    @BindView(R.id.iv_shuinuan_guanji)
    ImageView iv_shuinuan_guanji;
    @BindView(R.id.tv_shuinuan_guanji)
    TextView tv_shuinuan_guanji;
    @BindView(R.id.rv_shuinuan_guanji)
    RelativeLayout rv_shuinuan_guanji;
    @BindView(R.id.tv_shuinuan_shuibeng)
    TextView tv_shuinuan_shuibeng;
    @BindView(R.id.tv_shuinuan_youbeng)
    TextView tv_shuinuan_youbeng;
    @BindView(R.id.tv_wendu_yushe)
    TextView tv_wendu_yushe;
    @BindView(R.id.tv_wendu_dangqian)
    TextView tv_wendu_dangqian;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.tv_dianya)
    TextView tv_dianya;
    @BindView(R.id.tv_jinshuikou_wendu)
    TextView tv_jinshuikou_wendu;
    @BindView(R.id.tv_chushuikou_wendu)
    TextView tv_chushuikou_wendu;
    @BindView(R.id.tv_haibagaodu)
    TextView tv_haibagaodu;
    @BindView(R.id.tv_hanyangliang)
    TextView tv_hanyangliang;
    @BindView(R.id.tv_daqiya)
    TextView tv_daqiya;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_main;
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mImmersionBar.statusBarDarkFont(true);
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.rl_set, R.id.iv_shebeima, R.id.rv_shuinuan_kaiji, R.id.rv_shuinuan_guanji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_set:
                break;
            case R.id.iv_shebeima:
                break;
            case R.id.rv_shuinuan_kaiji:
                break;
            case R.id.rv_shuinuan_guanji:
                break;
        }
    }
}
