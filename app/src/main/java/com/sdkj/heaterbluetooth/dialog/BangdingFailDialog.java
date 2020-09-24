package com.sdkj.heaterbluetooth.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;

import java.util.List;


public class BangdingFailDialog extends Dialog implements View.OnClickListener {

    public TextView tv_content;
    public TextView bt_ok;
    protected boolean dismissAfterClick = true;


    public BangdingFailDialog(Context context) {
        this(context, R.style.dialogBaseBlur);
    }

    public BangdingFailDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_bangding_fail);
        tv_content = findViewById(R.id.tv_content);
        bt_ok = findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_ok) {
            dismissAfterClick();
        }
    }

    protected void dismissAfterClick() {
        if (dismissAfterClick) {
            dismiss();
        }
    }

    /**
     * 设置是否点击按钮后自动关闭窗口,默认true(是)
     */
    public BangdingFailDialog setDismissAfterClick(boolean dismissAfterClick) {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setTextContent(String msg) {
        tv_content.setText(msg);
    }
}
