/**
 *
 */
package com.sdkj.heaterbluetooth.config;

import android.content.Context;

import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.model.LoginUser;



/**
 * 登陆用户信息管理类
 */
public class UserManager {

    private static UserManager mUserManager;
    private static Context mContext;

    private UserManager(Context ctx) {
        mContext = ctx;
    }

    public static UserManager getManager(Context ctx) {
        if (mUserManager == null) {
            mUserManager = new UserManager(ctx);
        }
        return mUserManager;
    }


    public String getUserId() {
        return PreferenceHelper.getInstance(mContext).getString("of_user_id", "");
    }

    public String getUserIdKey() {
        return PreferenceHelper.getInstance(mContext).getString("user_id_key", "");
    }

    public String getPowerState() {
        return PreferenceHelper.getInstance(mContext).getString("power_state", "");
    }

    public String getAppToken() {
        return PreferenceHelper.getInstance(mContext).getString("app_token", "");
    }


    public String getUserName(){
        return PreferenceHelper.getInstance(mContext).getString("user_name", "");
    }

    public String getRongYun(){
        return PreferenceHelper.getInstance(mContext).getString("token_rong", "");
    }

    //保存用户信息
    public void saveUser(LoginUser.DataBean user) {
        if (user != null) {
            PreferenceHelper.getInstance(mContext).putString("of_user_id", user.getOf_user_id());
            PreferenceHelper.getInstance(mContext).putString("app_token", user.getApp_token());
            PreferenceHelper.getInstance(mContext).putString("user_name", user.getUser_name());
            PreferenceHelper.getInstance(mContext).putString("power_state_name", user.getPower_state());
            PreferenceHelper.getInstance(mContext).putString("token_rong", user.getToken_rong());
            PreferenceHelper.getInstance(mContext).putString("accid", user.getAccid());
            PreferenceHelper.getInstance(mContext).putString("user_id_key", user.getUser_id_key());
            PreferenceHelper.getInstance(mContext).putString("server_id", user.getServer_id());
            PreferenceHelper.getInstance(mContext).putString("power_state", user.getPower_state());

          //  Log.i("server_id1", PreferenceHelper.getInstance(mContext).getString("server_id", user.getServer_id()));
        }


    }

    /**
     * 删除用户信息
     */
    public void removeUser() {
        PreferenceHelper.getInstance(mContext).removeKey("of_user_id");
        PreferenceHelper.getInstance(mContext).removeKey("app_token");
        PreferenceHelper.getInstance(mContext).removeKey("user_name");
        PreferenceHelper.getInstance(mContext).removeKey("power_state_name");
        PreferenceHelper.getInstance(mContext).removeKey("token_rong");
        PreferenceHelper.getInstance(mContext).removeKey("accid");
        PreferenceHelper.getInstance(mContext).removeKey("user_id_key");
        PreferenceHelper.getInstance(mContext).removeKey("server_id");
        PreferenceHelper.getInstance(mContext).removeKey("power_state");
        PreferenceHelper.getInstance(mContext).removeKey("car_server_id");
    }

}