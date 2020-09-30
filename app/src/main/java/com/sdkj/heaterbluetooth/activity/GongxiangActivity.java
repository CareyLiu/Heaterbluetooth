package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.ServiceModel;
import com.sdkj.heaterbluetooth.util.Y;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class GongxiangActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.ll_zanwu)
    LinearLayout ll_zanwu;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.sm_shebei_list)
    SmartRefreshLayout sm_shebei_list;
    private String ccidMa;

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_gongxiang_list;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String ccid) {
        Intent intent = new Intent(context, GongxiangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ccid", ccid);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ccidMa = getIntent().getStringExtra("ccid");
        getData();
        initAdapter();
        initSM();
    }

    private void initSM() {
        sm_shebei_list.setEnableLoadMore(false);
        sm_shebei_list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
    }

    private void initAdapter() {

    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03511");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("ccid", ccidMa);
        Gson gson = new Gson();
        OkGo.<AppResponse<ServiceModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ServiceModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ServiceModel.DataBean>> response) {
                        List<ServiceModel.DataBean> data = response.body().data;

                        if (data.size() > 0) {
                            ll_zanwu.setVisibility(View.GONE);
                            rv_content.setVisibility(View.VISIBLE);
                        } else {
                            ll_zanwu.setVisibility(View.VISIBLE);
                            rv_content.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ServiceModel.DataBean>> response) {
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        sm_shebei_list.finishRefresh();
                    }
                });
    }


    @OnClick({R.id.rl_back, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_add:
                GongxiangAddActivity.actionStart(mContext, ccidMa);
                break;
        }
    }
}
