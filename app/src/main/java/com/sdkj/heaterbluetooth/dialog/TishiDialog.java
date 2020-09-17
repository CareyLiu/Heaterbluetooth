package com.sdkj.heaterbluetooth.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;


public class TishiDialog extends Dialog implements View.OnClickListener {
    public final static int TYPE_XIAOXI = 1;
    public final static int TYPE_FAILED = 2;
    public final static int TYPE_CAOZUO = 3;
    public final static int TYPE_SUCESS = 4;
    public final static int TYPE_DELETE = 5;

    public TextView tv_content;
    public TextView tv_title;
    public TextView tv_cancel;
    public TextView tv_confirm;
    public ImageView iv_img;

    private TishiDialogListener mListener;
    protected boolean dismissAfterClick = true;//是否点击按钮后关闭
    private int type;//1.消息推送    2.操作失败    3.操作提示    4.操作成功    5.删除

    public TishiDialog(Context context, int type, TishiDialogListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
        this.type = type;
        init();
    }

    private TishiDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.dialog_tishi);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        iv_img = findViewById(R.id.iv_img);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);

        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);

        Log.i("了科技法典里会计法地方都是" + type, "");
        if (type == TYPE_XIAOXI) {//1.消息推送    2.操作失败    3.操作提示    4.操作成功    5.删除
            setTextTitle("消息推送").setTextContent("为您提供最新消息通知").setTextConfirm("确定").setTextCancel("取消").setImgPic(R.mipmap.alert_pic_xiaoxi);
            iv_img.setImageResource(R.mipmap.alert_pic_xiaoxi);
        } else if (type == TYPE_FAILED) {
            setTextTitle("操作失败").setTextContent("操作失败，请稍后再重试").setTextConfirm("确定").setTextCancel("").setImgPic(R.mipmap.alert_pic_failed);
            iv_img.setImageResource(R.mipmap.alert_pic_failed);
        } else if (type == TYPE_CAOZUO) {
            setTextTitle("提示").setTextContent("确认此操作？").setTextConfirm("确定").setTextCancel("取消").setImgPic(R.mipmap.alert_pic_caozuotishi);
            iv_img.setImageResource(R.mipmap.alert_pic_caozuotishi);
        } else if (type == TYPE_SUCESS) {
            setTextTitle("操作成功").setTextContent("恭喜您操作成功").setTextConfirm("确定").setTextCancel("").setImgPic(R.mipmap.alert_pic_sucess);
            iv_img.setImageResource(R.mipmap.alert_pic_sucess);
        } else if (type == TYPE_DELETE) {
            setTextTitle("删除").setTextContent("是否删除？").setTextConfirm("确定").setTextCancel("取消").setImgPic(R.mipmap.alert_pic_delete);
            iv_img.setImageResource(R.mipmap.alert_pic_delete);
        } else {
            iv_img.setImageResource(R.mipmap.alert_pic_delete);
        }
    }

    // ---------------------------text
    public TishiDialog setTextTitle(String text) {//提示标题
        tv_title.setText(text);
        return this;
    }

    public TishiDialog setTextCancel(String text) {//取消按钮  传空字符串隐藏
        if (TextUtils.isEmpty(text)) {
            tv_cancel.setVisibility(View.GONE);
        } else {
            tv_cancel.setVisibility(View.VISIBLE);
            tv_cancel.setText(text);
        }
        return this;
    }

    public TishiDialog setTextConfirm(String text) {//确定按钮
        tv_confirm.setText(text);
        return this;
    }

    public TishiDialog setTextContent(String text) {//提示内容
        tv_content.setText(text);
        return this;
    }

    public TishiDialog setImgPic(int imageResource) {//设置本地图片
        iv_img.setImageResource(imageResource);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            clickCancel(v);
        } else if (v == tv_confirm) {
            clickConfirm(v);
        }
    }

    private void clickCancel(View v) {//取消按钮
        if (mListener != null) {
            mListener.onClickCancel(v, TishiDialog.this);
        }
        dismissAfterClick();
    }

    private void clickConfirm(View v) {//确定按钮
        if (mListener != null) {
            mListener.onClickConfirm(v, TishiDialog.this);
        }
        dismissAfterClick();
    }

    protected void dismissAfterClick() {
        if (dismissAfterClick) {
            dismiss();
        }
    }

    /**
     * 设置是否点击按钮后自动关闭窗口,默认true(是)
     */
    public TishiDialog setDismissAfterClick(boolean dismissAfterClick) {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }


    public interface TishiDialogListener {
        void onClickCancel(View v, TishiDialog dialog);

        void onClickConfirm(View v, TishiDialog dialog);

        void onDismiss(TishiDialog dialog);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mListener != null) {
            mListener.onDismiss(TishiDialog.this);
        }
    }
}
