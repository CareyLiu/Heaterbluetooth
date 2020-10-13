package com.sdkj.heaterbluetooth.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.activity.shuinuan.ShuinuanHostActivity;
import com.sdkj.heaterbluetooth.activity.shuinuan.ShuinuanZhuangtaiActivity;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.GongxiangModel;
import com.sdkj.heaterbluetooth.util.RxBus;
import com.sdkj.heaterbluetooth.util.Y;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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
    @BindView(R.id.tv_shebeima)
    TextView tvShebeima;
    @BindView(R.id.rl_zhujicanshu)
    RelativeLayout rlZhujicanshu;
    @BindView(R.id.rl_gongxiang)
    RelativeLayout rlGongxiang;
    @BindView(R.id.rl_gongxiang_jie)
    RelativeLayout rlGongxiangJie;
    private String ccid;

    public static final int TYPE_FENGNUAN = 1;
    public static final int TYPE_SHUINUAN = 2;
    private int type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getIntExtra("type", 0);

        if (type == TYPE_SHUINUAN) {
            rlGuzhang.setVisibility(View.GONE);
            rlZhujicanshu.setVisibility(View.VISIBLE);
        } else if (type == TYPE_FENGNUAN) {
            rlGuzhang.setVisibility(View.VISIBLE);
            rlZhujicanshu.setVisibility(View.GONE);
        }

        String share_type = PreferenceHelper.getInstance(mContext).getString("share_type", "");
        if (share_type.equals("2")) {
            rlGongxiang.setVisibility(View.GONE);
            rlJiebangshebei.setVisibility(View.GONE);
            rlGongxiangJie.setVisibility(View.VISIBLE);
        } else {
            rlGongxiang.setVisibility(View.VISIBLE);
            rlJiebangshebei.setVisibility(View.VISIBLE);
            rlGongxiangJie.setVisibility(View.GONE);
        }

        ccid = PreferenceHelper.getInstance(this).getString("ccid", "");
        tvShebeima.setText(ccid);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_JIEBANG) {
                    finish();
                }
            }
        }));

        rlDingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FengnuandishiActivity.actionStart(mContext);
            }
        });
        rlJiareqicanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == TYPE_SHUINUAN) {
                    ShuinuanZhuangtaiActivity.actionStart(mContext);
                } else if (type == TYPE_FENGNUAN) {
                    JiaReQiCanShuActivity.actionStart(mContext);
                }
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

        rlZhujicanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShuinuanHostActivity.actionStart(mContext);
            }
        });

        rlGongxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GongxiangActivity.actionStart(mContext, ccid);
            }
        });

        rlGongxiangJie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
                    @Override
                    public void onClickCancel(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onClickConfirm(View v, TishiDialog dialog) {
                        Map<String, String> map = new HashMap<>();
                        map.put("code", "03514");
                        map.put("key", Urls.key);
                        map.put("token", UserManager.getManager(mContext).getAppToken());
                        map.put("ccid", ccid);
                        Gson gson = new Gson();
                        OkGo.<AppResponse<GongxiangModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                                .tag(this)//
                                .upJson(gson.toJson(map))
                                .execute(new JsonCallback<AppResponse<GongxiangModel.DataBean>>() {
                                    @Override
                                    public void onSuccess(final Response<AppResponse<GongxiangModel.DataBean>> response) {
                                        Y.t("退出成功");
                                        Notice n = new Notice();
                                        n.type = ConstanceValue.MSG_JIEBANG;
                                        RxBus.getDefault().sendRx(n);
                                    }

                                    @Override
                                    public void onError(Response<AppResponse<GongxiangModel.DataBean>> response) {
                                        Y.tError(response);
                                    }

                                    @Override
                                    public void onFinish() {
                                        super.onFinish();
                                        dismissProgressDialog();
                                    }

                                    @Override
                                    public void onStart(Request<AppResponse<GongxiangModel.DataBean>, ? extends Request> request) {
                                        super.onStart(request);
                                        showProgressDialog();
                                    }
                                });
                    }

                    @Override
                    public void onDismiss(TishiDialog dialog) {

                    }
                });
                dialog.setTextContent("是否退出该共享设备");
                dialog.show();
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
    public static void actionStart(Context context, int type) {
        Intent intent = new Intent(context, SheBeiSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("type", type);
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
