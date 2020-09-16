package com.sdkj.heaterbluetooth.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.basepage.HomeBasicActivity;
import com.sdkj.heaterbluetooth.callback.JsonCallback;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.config.AppResponse;
import com.sdkj.heaterbluetooth.config.UserManager;
import com.sdkj.heaterbluetooth.getnet.Urls;
import com.sdkj.heaterbluetooth.model.LoginUser;


import java.util.HashMap;
import java.util.Map;

import static com.sdkj.heaterbluetooth.getnet.Urls.SERVER_URL;


public class LoginBtnView extends LinearLayout {
    private Context mContext;
    private View mView;

    private View ll_main;
    private TextView tv_name;
    private ImageView iv_img;

    private LoginUser.DataBean user;
    private BaseActivity activity;

    public LoginBtnView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LoginBtnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LoginBtnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.kefu_view_loginbt, this, true);
        ll_main = mView.findViewById(R.id.ll_main);
        tv_name = mView.findViewById(R.id.tv_name);
        iv_img = mView.findViewById(R.id.iv_img);

        ll_main.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity != null) {
                    login(user.getSubsystem_id(), user.getUser_id_key(), user.getPower_state());
                }
            }
        });
    }

    public void setUser(LoginUser.DataBean user, BaseActivity activity) {
        this.user = user;
        this.activity = activity;

        String power_state_name = user.getPower_state_name();
        String power_state = user.getPower_state();

        tv_name.setText(power_state_name);
        if (power_state.equals("1")) {//聚易佳
            iv_img.setImageResource(R.mipmap.login_icon_chezhu);
        } else if (power_state.equals("2")) {//维修厂
            iv_img.setImageResource(R.mipmap.login_icon_qiche);
        } else {//客服端
            iv_img.setImageResource(R.mipmap.login_icon_kefu);
        }
    }

    /**
     * §6.5	车主端，代理商端，维修端，商家端四端合一登陆
     * <p>
     * code	请求码(00051)
     * key	身份标识
     * power_state	登录类型（1.客户 2.修配厂3.智能家居代理商 4.商城商家）
     * subsystem_id	子系统id(写死)（那个子系统就传那个）
     * 维修厂端传witwork  代理商端传wit 商家端传jcz
     * 车主端没有，传空
     * user_id_key	加密的用户id
     * phone_model	手机型号
     */
    private void login(String subsystem_id, String user_id_key, final String power_state) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00051");
        map.put("key", Urls.key);
        map.put("subsystem_id", subsystem_id);
        map.put("user_id_key", user_id_key);
        map.put("power_state", power_state);
        Gson gson = new Gson();
        OkGo.<AppResponse<LoginUser.DataBean>>post(SERVER_URL + "index/login")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<LoginUser.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<LoginUser.DataBean>> response) {
                        UserManager.getManager(activity).saveUser(response.body().data.get(0));
                        PreferenceHelper.getInstance(activity).putString("power_state", power_state);
                        switch (power_state) {
                            case "1"://聚易佳
                                activity.startActivity(new Intent(activity, HomeBasicActivity.class));
                                activity.finish();
                                break;
                            case "2"://维修厂
                                break;
                            case "3"://客服端
                            //    activity.startActivity(new Intent(activity, ServiceActivity.class));
                             //   activity.finish();
                                break;
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<LoginUser.DataBean>> response) {
                        UIHelper.ToastMessage(activity, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<LoginUser.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                });
    }
}
