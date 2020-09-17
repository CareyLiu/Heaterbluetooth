package com.sdkj.heaterbluetooth.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;


import com.sdkj.heaterbluetooth.app.BaseActivity;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.dialog.TishiDialog;

import java.text.DecimalFormat;

public class ShuinuanBaseActivity extends BaseActivity {

    public static String SN_Send = "wh/hardware/8/aaaaaaaaaaaaaaaa20040018";
    public static String SN_Accept = "wh/app/8/aaaaaaaaaaaaaaaa20040018";
    public static String ccid = "aaaaaaaaaaaaaaaa20040018";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
        initDialogClick();
    }


    public ProgressDialog dialog;

    public void initDialog() {
        dialog = new ProgressDialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
    }

    public void showDialog(String msg) {
        dialog.setMessage(msg);
        dialog.show();
    }

    public ProgressDialog dialogClick;

    public void initDialogClick() {
        dialogClick = new ProgressDialog(mContext);
        dialogClick.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogClick.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogClick.setCanceledOnTouchOutside(false);
        dialogClick.setCancelable(true);
    }

    public void showDialogClick(String msg) {
        dialogClick.setMessage(msg);
        dialogClick.show();
    }

    public int getInt(String content) {
        try {
            return Integer.parseInt(content);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float getFloat(String content) {
        try {
            return Float.parseFloat(content);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void t(String msg) {
        UIHelper.ToastMessage(this, msg);
    }

    public String formatNum(float num) {
        String format = new DecimalFormat("#.#").format(num);
        return format;
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
