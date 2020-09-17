package com.sdkj.heaterbluetooth.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.activity.FengNuanActivity;
import com.sdkj.heaterbluetooth.adapter.SheBeiListAdapter;
import com.sdkj.heaterbluetooth.app.AppManager;
import com.sdkj.heaterbluetooth.app.ConstanceValue;
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
import com.sdkj.heaterbluetooth.model.SmartDevice_car_0364;
import com.sdkj.heaterbluetooth.util.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SheBeiFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.list)
    RecyclerView mList;

    List<SmartDevice_car_0364.DataBean> carList = new ArrayList<>();//车联网列表数据源
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.rl_main)
    LinearLayout rlMain;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    private SheBeiListAdapter sheBeiListAdapter;
    private List<SheBeiModel> mDatas = new ArrayList<>();

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_kongzhiye;
    }

    @Override
    protected void initView(View view) {
        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
        unbinder = ButterKnife.bind(this, view);

        sheBeiListAdapter = new SheBeiListAdapter(R.layout.item_shebei, R.layout.item_shebei_header, mDatas);

//        carListAdapter = new CarList1Adapter(carList);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList.setAdapter(sheBeiListAdapter);


        sheBeiListAdapter.notifyDataSetChanged();
        sheBeiListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        if (mDatas.get(position).device_type.equals("1")) {
                            PreferenceHelper.getInstance(getActivity()).putString("ccid", mDatas.get(position).ccid);
                            int i = mDatas.get(position).ccid.length() - 1;
                            String str = String.valueOf(mDatas.get(position).ccid.charAt(i));
                            Log.i("serverId", str);
                            PreferenceHelper.getInstance(getActivity()).putString("car_server_id", str + "/");
                            if (NetworkUtils.isConnected(getActivity())) {
                                Activity currentActivity = AppManager.getAppManager().currentActivity();
                                if (currentActivity != null) {
                                    //startActivity(new Intent(getActivity(), WindHeaterActivity.class));
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

                        FengNuanActivity.actionStart(getActivity());
                        break;
                }
            }
        });


        getSheBeiData();
//        carListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                PreferenceHelper.getInstance(getActivity()).putString("ccid", carList.get(position).getCcid());
//                PreferenceHelper.getInstance(getActivity()).putString("car_id", carList.get(position).getUser_car_id());
//                PreferenceHelper.getInstance(getActivity()).putString("atmos", carList.get(position).getZhu_apc());
//                PreferenceHelper.getInstance(getActivity()).putString("latitude", carList.get(position).getGaode_x());
//                PreferenceHelper.getInstance(getActivity()).putString("longitude", carList.get(position).getGaode_y());
//                PreferenceHelper.getInstance(getActivity()).putString("car_server_id", carList.get(position).getServer_id());
//                PreferenceHelper.getInstance(getActivity()).putString("car_number", carList.get(position).getPlate_number());
//                PreferenceHelper.getInstance(getActivity()).putString("name", carList.get(position).getCar_brand_name());
//
//
//                startActivity(new Intent(getActivity(), WindHeaterActivity.class));
//
//
//                CAR_CTROL = "wit/cbox/hardware/" + getServer_id() + getCcid();
//                Log.i("getInformation", "CAR_CTROL     " + CAR_CTROL);
//
//                CARBOX_GETNOW = "wit/cbox/app/" + getServer_id() + getCcid();
//                Log.i("getInformation", "CAR_CTROL     " + CARBOX_GETNOW);
//
//
//                /**
//                 * 向客户订阅地址发送车辆时时数据 汽车盒子刚刚启动->接入互联网->订阅本身地址->自己给自己订阅的地址发送心跳,发送，自己接收到了g.说明自己和MQTT连接在线
//                 */
//            }
//        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CarBrandActivity.actionStart(getActivity());

                TianJiaSheBeiDialog tianJiaSheBeiDialog = new TianJiaSheBeiDialog(getActivity(), new TianJiaSheBeiDialog.OnDialogItemClickListener() {
                    @Override
                    public void clickFengNuan() {
                        //  UIHelper.ToastMessage(getActivity(), "点击了风暖");
                        // BindBoxActivity.actionStart(getActivity());

                    }

                    @Override
                    public void clickShuiNuan() {
                        // UIHelper.ToastMessage(getActivity(), "点击了水暖");
                        // BindBoxActivity.actionStart(getActivity());
                    }

                    @Override
                    public void clickKongTiao() {
                        //UIHelper.ToastMessage(getActivity(), "点击了空调");
                        //  BindBoxActivity.actionStart(getActivity());
                    }

                    @Override
                    public void clickKongChe() {
                        //UIHelper.ToastMessage(getActivity(), "点击了空调");
                        //  BindBoxActivity.actionStart(getActivity());
                    }
                });
                tianJiaSheBeiDialog.show();
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ADD_CHELIANG_SUCCESS) {
                    //车辆添加成功
                    srLSmart.autoRefresh();

                }
            }
        }));

        srLSmart.setEnableRefresh(true);
        srLSmart.setEnableLoadMore(false);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getSheBeiData();
            }
        });


    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }


    @Override
    protected boolean immersionEnabled() {
        return true;
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        //  Log.i("supportVisible", "visible");

        getSheBeiData();
    }


//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        DataIn in = new DataIn();
//        in.code = "03064";
//        in.key = Urls.key;
//        in.user_car_type = "1";
//        in.token = UserManager.getManager(getActivity()).getAppToken();
//        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
//        final Request request = new Request.Builder().url(Urls.SERVER_URL + "wit/app/user").post(body).build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Gson gson = new Gson();
//                SmartDevice_car_0364 bean = gson.fromJson(response.body().string(), SmartDevice_car_0364.class);
//
//
//                carList.clear();
//                carList.addAll(bean.getData());
//
//                getActivity().runOnUiThread(new Runnable() {
//                    public void run() {
//                        if (carList.size() == 0) {
//                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
//                            carListAdapter.setHeaderView(view);
//                        }
//                        carListAdapter.notifyDataSetChanged();
//                    }
//                });
//
//            }
//        });
    //}

    public void getSheBeiData() {
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
                            sheBeiListAdapter.setHeaderView(view);
                        } else {
                            sheBeiListAdapter.removeAllHeaderView();
                        }
                        sheBeiListAdapter.setNewData(mDatas);
                        sheBeiListAdapter.notifyDataSetChanged();
                        srLSmart.finishRefresh();
                    }


                    @Override
                    public void onError(Response<AppResponse<SheBeiLieBieListModel.DataBean>> response) {
                        UIHelper.ToastMessage(getActivity(), response.getException().getMessage());
                    }
                });

//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        DataIn in = new DataIn();
//        in.code = "03064";
//        in.key = Urls.key;
//        in.user_car_type = "1";
//        in.token = UserManager.getManager(getActivity()).getAppToken();
//        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
//        final Request request = new Request.Builder().url(Urls.SERVER_URL + "wit/app/user").post(body).build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Gson gson = new Gson();
//                SmartDevice_car_0364 bean = gson.fromJson(response.body().string(), SmartDevice_car_0364.class);
//
//
//                carList.clear();
//                carList.addAll(bean.getData());
//
//                getActivity().runOnUiThread(new Runnable() {
//                    public void run() {
//                        if (carList.size() == 0) {
//                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
//                            carListAdapter.setHeaderView(view);
//                        }
//                        carListAdapter.notifyDataSetChanged();
//                    }
//                });
//
//            }
//        });

    }

    public static SheBeiFragment newInstance() {
        Bundle args = new Bundle();
        SheBeiFragment fragment = new SheBeiFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
