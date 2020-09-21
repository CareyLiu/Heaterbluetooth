package com.sdkj.heaterbluetooth.activity.shuinuan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;


import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;
import com.sdkj.heaterbluetooth.util.Y;

import java.text.DecimalFormat;

public class ShuinuanBaseActivity extends BaseActivity {

    public static String SN_Send;//"wh/hardware/";
    public static String SN_Accept;//"wh/app/";
    public static String ccid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void chenggong() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {

            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.show();
    }
}
