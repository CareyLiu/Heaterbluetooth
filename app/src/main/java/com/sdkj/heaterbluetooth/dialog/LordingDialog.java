package com.sdkj.heaterbluetooth.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;


/**
 * 带环形进度条，和信息提示的窗口
 */
public class LordingDialog extends Dialog {

    private TextView tv_msg;
    private ProgressBar progress;

    public LordingDialog setTextMsg(String msg) {
        if (TextUtils.isEmpty(msg)) {
            tv_msg.setVisibility(View.GONE);
        } else {
            tv_msg.setVisibility(View.VISIBLE);
            tv_msg.setText(msg);
        }
        return this;
    }

    public LordingDialog(Context context) {
        super(context);
        init();
    }

    public LordingDialog(Context context, int type) {
        super(context);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_progress, getLayoutParamsViewGroupWW());
        tv_msg = (TextView) findViewById(R.id.dialog_progress_tv_msg);
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setIndeterminateDrawable(getContext().getResources().getDrawable(R.drawable.dialog_progress_rotate));
//        SDDrawable drawable = new SDDrawable().color(Color.parseColor("#55000000")).cornerAll(SDViewUtil.dp2px(5));
//        SDViewUtil.setBackgroundDrawable(getContentView(), drawable);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    public void setContentView(int layoutResID, ViewGroup.LayoutParams params) {
        View view = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        view.setBackgroundColor(Color.parseColor("#000000"));
        this.setContentView(view, params);
    }

    // ViewGroup
    public ViewGroup.LayoutParams getLayoutParamsViewGroupWW() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
