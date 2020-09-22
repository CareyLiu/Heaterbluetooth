package com.sdkj.heaterbluetooth.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sdkj.heaterbluetooth.R;


public class FuWuDialog extends Dialog implements View.OnClickListener {


    public TextView tv_cancel;
    public TextView tv_confirm;

    private TextView tvYinSi;
    private TextView tvYongHuXieYi;


    private FuWuDiaLogClikListener mListener;
    protected boolean dismissAfterClick = true;//是否点击按钮后关闭
    private int type;//1.消息推送    2.操作失败    3.操作提示    4.操作成功    5.删除

    public FuWuDialog(Context context, FuWuDiaLogClikListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
        this.type = type;
        init();
    }

    private FuWuDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.dialog_yinsi_fuwu);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);
        tvYinSi = findViewById(R.id.tv_yinsi);
        tvYongHuXieYi = findViewById(R.id.tv_yonghushiyong);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickConfirm();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickCancel();
            }
        });
        tvYinSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.yinsixieyi();
            }
        });

        tvYongHuXieYi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.fuwu();
            }
        });
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
            mListener.onClickCancel();
        }
        dismissAfterClick();
    }

    private void clickConfirm(View v) {//确定按钮
        if (mListener != null) {
            mListener.onClickConfirm();
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
    public FuWuDialog setDismissAfterClick(boolean dismissAfterClick) {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }


    public interface FuWuDiaLogClikListener {

        void onClickCancel();

        void onClickConfirm();

        void onDismiss(FuWuDialog dialog);

        void fuwu();

        void yinsixieyi();
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mListener != null) {
            mListener.onDismiss(FuWuDialog.this);
        }
    }
}
