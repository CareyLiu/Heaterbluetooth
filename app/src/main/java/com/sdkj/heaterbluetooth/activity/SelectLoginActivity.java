package com.sdkj.heaterbluetooth.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.model.LoginUser;
import com.sdkj.heaterbluetooth.view.LoginBtnView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sl on 2019/6/10.
 * 四合一登录
 */
public class SelectLoginActivity extends BaseActivity {


    @BindView(R.id.bt1)
    LoginBtnView bt1;
    @BindView(R.id.bt2)
    LoginBtnView bt2;
    @BindView(R.id.bt3)
    LoginBtnView bt3;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view_close)
    RelativeLayout view_close;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_select_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        if (LoginActivity.userlist.size() == 2) {
            bt3.setVisibility(View.GONE);
            view.setVisibility(View.GONE);

            LoginUser.DataBean user1 = LoginActivity.userlist.get(0);
            LoginUser.DataBean user2 = LoginActivity.userlist.get(1);

            bt1.setUser(user1, this);
            bt2.setUser(user2, this);
        } else if (LoginActivity.userlist.size() == 3) {
            bt3.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);

            LoginUser.DataBean user1 = LoginActivity.userlist.get(0);
            LoginUser.DataBean user2 = LoginActivity.userlist.get(1);
            LoginUser.DataBean user3 = LoginActivity.userlist.get(2);

            bt1.setUser(user1, this);
            bt2.setUser(user2, this);
            bt3.setUser(user3, this);
        }
    }

    @OnClick(R.id.view_close)
    public void onViewClicked() {
        finish();
    }
}
