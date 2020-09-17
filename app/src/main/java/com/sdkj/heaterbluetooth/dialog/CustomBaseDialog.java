package com.sdkj.heaterbluetooth.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.widget.base.BaseDialog;
import com.sdkj.heaterbluetooth.R;


import butterknife.BindView;
import butterknife.ButterKnife;


public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {


    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    View.OnClickListener cancelClickListener;
    View.OnClickListener confirmClickListener;

    private String title,content;
    private Drawable drawable;

    public CustomBaseDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        showAnim(new BounceBottomEnter());
        dismissAnim(new SlideBottomExit());
        View inflate = View.inflate(mContext, R.layout.dialog_custom,null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        if (cancelClickListener == null){
            btnCancel.setVisibility(View.GONE);
        }else {
            btnCancel.setOnClickListener(cancelClickListener);
        }
        btnConfirm.setOnClickListener(confirmClickListener);
        tvTitle.setText(title);
        tvContent.setText(content);
        ivPic.setBackground(drawable);

    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setPic(Drawable drawable){
        this.drawable = drawable;
    }
    public void setCancelClickListener(View.OnClickListener onClickListener){
        this.cancelClickListener = onClickListener;
    }
    public void setConfirmClickListener(View.OnClickListener onClickListener){
        this.confirmClickListener = onClickListener;
    }
}
