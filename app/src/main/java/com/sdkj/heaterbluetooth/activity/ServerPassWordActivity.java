package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.flyco.roundview.RoundTextView;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.common.UIHelper;


import butterknife.BindView;

public class ServerPassWordActivity extends BaseActivity {

    @BindView(R.id.tv_fuwupassword_huashu)
    TextView tvFuwupasswordHuashu;
    @BindView(R.id.et_edit)
    EditText etEdit;
    @BindView(R.id.rt_textview)
    RoundTextView rtTextview;
    String str;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_heater_password;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.blue).init();
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String str) {

        Intent intent = new Intent(context, ServerPassWordActivity.class);
        intent.putExtra("strClass", str);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        str = getIntent().getStringExtra("strClass");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rtTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEdit.getText().toString().equals("123456")) {
                    if (str.equals("host")) {
                        finish();
                        HostActivity.actionStart(ServerPassWordActivity.this);
                    } else if (str.equals("RatioActivity")) {//风油比参数
                        finish();
                        RatioActivity.actionStart(ServerPassWordActivity.this);
                    } else if (str.equals("AtmosActivity")) {//大气压参数
                        finish();
                        AtmosActivity.actionStart(ServerPassWordActivity.this);

                    }
                } else {
                    UIHelper.ToastMessage(ServerPassWordActivity.this, "请输入正确的密码", Toast.LENGTH_SHORT);
                }


            }
        });

    }
}
