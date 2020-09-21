package com.sdkj.heaterbluetooth.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.activity.BindBoxActivity;
import com.sdkj.heaterbluetooth.activity.FengNuanActivity;
import com.sdkj.heaterbluetooth.activity.FengnuanJieActivity;
import com.sdkj.heaterbluetooth.activity.FengnuandishiActivity;
import com.sdkj.heaterbluetooth.activity.SheBeiSetActivity;
import com.sdkj.heaterbluetooth.adapter.ShebeiAdapter;
import com.sdkj.heaterbluetooth.adapter.ShebeiNewAdapter;
import com.sdkj.heaterbluetooth.app.AppManager;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
import com.sdkj.heaterbluetooth.app.MyApplication;
import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basicmvp.BaseFragment;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.dialog.TianJiaSheBeiDialog;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.SheBeiLieBieListModel;
import com.sdkj.heaterbluetooth.model.SheBeiModel;
import com.sdkj.heaterbluetooth.util.NetworkUtils;
import com.sdkj.heaterbluetooth.util.RxBus;
import com.sdkj.heaterbluetooth.util.Y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sdkj.heaterbluetooth.app.MyApplication.getCcid;
import static com.sdkj.heaterbluetooth.app.MyApplication.getServer_id;

public class ShebeiFrament extends BaseTwoFragment {
    @BindView(R.id.iv_login)
    ImageView iv_login;
    @BindView(R.id.ll_shebei_no)
    FrameLayout ll_shebei_no;
    @BindView(R.id.ll_add_shebei)
    LinearLayout ll_add_shebei;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.sm_shebei_list)
    SmartRefreshLayout sm_shebei_list;

    private List<SheBeiModel> mDatas = new ArrayList<>();
    private ShebeiNewAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_shebei;
    }

    public static ShebeiFrament newInstance() {
        Bundle args = new Bundle();
        ShebeiFrament fragment = new ShebeiFrament();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initAdapter();
        initSm();
        initHuidiao();
        return rootView;
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_JIEBANG || message.type == ConstanceValue.MSG_SHUA) {
                    getShebeiList();
                }
            }
        }));
    }

    private void initSm() {
        sm_shebei_list.setEnableLoadMore(false);
        sm_shebei_list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getShebeiList();
            }
        });
    }

    private void initState() {
        if (UserManager.getManager(getActivity()).isLogin()) {
            ll_shebei_no.setVisibility(View.GONE);
            sm_shebei_list.setVisibility(View.VISIBLE);
            getShebeiList();
        } else {
            ll_shebei_no.setVisibility(View.VISIBLE);
            sm_shebei_list.setVisibility(View.GONE);
        }
    }

    private void initAdapter() {
        adapter = new ShebeiNewAdapter(R.layout.item_shebei_new, R.layout.item_shebei_header, mDatas);
        rv_content.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mDatas.get(position).device_type.equals("1")) {
                    //  mDatas.get(position).ccid = "aaaaaaaaaaaaaaaa90070018";
                    PreferenceHelper.getInstance(getActivity()).putString("ccid", mDatas.get(position).ccid);
                    MyApplication.CARBOX_GETNOW = "wit/cbox/app/" + getServer_id() + getCcid();



                    int i = mDatas.get(position).ccid.length() - 1;
                    String str = String.valueOf(mDatas.get(position).ccid.charAt(i));
                    Log.i("serverId", str);
                    PreferenceHelper.getInstance(getActivity()).putString("car_server_id", str + "/");
                    if (NetworkUtils.isConnected(getActivity())) {
                        Activity currentActivity = AppManager.getAppManager().currentActivity();
                        if (currentActivity != null) {
                            FengNuanActivity.actionStart(getActivity());
                        }
                    } else {
                        UIHelper.ToastMessage(getActivity(), "请连接网络后重新尝试");
                    }
                } else if (mDatas.get(position).device_type.equals("6")) {
                    String ccid = mDatas.get(position).ccid;
                    int pos = ccid.length() - 1;
                    String count = String.valueOf(ccid.charAt(pos)) + "/";
                    PreferenceHelper.getInstance(getContext()).putString("ccid", mDatas.get(position).ccid);
                    PreferenceHelper.getInstance(getContext()).putString("car_server_id", count);
                    if (NetworkUtils.isConnected(getActivity())) {
                        Activity currentActivity = AppManager.getAppManager().currentActivity();
                        if (currentActivity != null) {
                            //ShuinuanMainActivity.actionStart(getActivity(), ccid, count);
                        }
                    } else {
                        UIHelper.ToastMessage(getActivity(), "请连接网络后重新尝试");
                    }
                }

            }
        });
    }

    private void getShebeiList() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03510");
        map.put("key", Urls.key);
        map.put("user_car_type", "1");
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<SheBeiLieBieListModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SheBeiLieBieListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SheBeiLieBieListModel.DataBean>> response) {
                        mDatas.clear();

                        for (int i = 0; i < response.body().data.size(); i++) {
                            SheBeiModel sheBeiModel = new SheBeiModel(true, response.body().data.get(i).getControl_device_name());
                            // Log.i("name", response.body().data.get(i).getControl_device_name());
                            mDatas.add(sheBeiModel);
                            for (int j = 0; j < response.body().data.get(i).getControl_device_list().size(); j++) {
                                // Log.i("name--shebei", response.body().data.get(i).getControl_device_list().get(j).getDevice_name());
                                SheBeiModel sheBeiModel1 = new SheBeiModel(false, response.body().data.get(i).getControl_device_name());
                                SheBeiLieBieListModel.DataBean.ControlDeviceListBean bean = response.body().data.get(i).getControl_device_list().get(j);
                                sheBeiModel1.ccid = bean.getCcid();
                                sheBeiModel1.device_img_url = bean.getDevice_img_url();
                                sheBeiModel1.device_name = bean.getDevice_name();
                                sheBeiModel1.validity_state = bean.getValidity_state();
                                sheBeiModel1.validity_term = bean.getValidity_term();
                                sheBeiModel1.validity_time = bean.getValidity_time();
                                sheBeiModel1.device_type = response.body().data.get(i).control_type_id;
                                mDatas.add(sheBeiModel1);
                            }
                        }

                        if (mDatas.size() == 0) {
                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
                            adapter.setHeaderView(view);
                        } else {
                            adapter.removeAllHeaderView();
                        }
                        adapter.setNewData(mDatas);
                        adapter.notifyDataSetChanged();
                    }


                    @Override
                    public void onError(Response<AppResponse<SheBeiLieBieListModel.DataBean>> response) {

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        sm_shebei_list.finishRefresh();
                    }
                });
    }

    @OnClick({R.id.iv_login, R.id.ll_add_shebei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login:
                clickLogin();
                break;
            case R.id.ll_add_shebei:
                addShebei();
                break;
        }
    }

    private void clickLogin() {
        Notice n = new Notice();
        n.type = ConstanceValue.MSG_LOGIN;
        RxBus.getDefault().sendRx(n);
    }

    private void addShebei() {
        BindBoxActivity.actionStart(getContext());
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        initState();
    }
}
