package com.sdkj.heaterbluetooth.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.sdkj.heaterbluetooth.R;


import java.util.List;

public class BottomDialog extends Dialog {

    private BottomDialogView view_bottom;

    public BottomDialog(Activity activity) {
        this(activity, R.style.dialogBaseBlur);
    }

    public BottomDialog(Activity activity, int theme) {
        super(activity, theme);
        setOwnerActivity(activity);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_bottom);
        view_bottom = findViewById(R.id.view_bottom);


        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
    }


    public void setClickListener(BottomDialogView.ClickListener clickListener) {
        view_bottom.setClickListener(clickListener);
    }

    public void setModles(List<String> models) {
        view_bottom.setModels(models);
    }


    /**
     * 显示在底部
     */
    public void showBottom() {
        setGrativity(Gravity.BOTTOM);
        setAnimations(R.style.dialogAnimation);
        show();
    }

    /**
     * 设置窗口显示的位置
     */
    public BottomDialog setGrativity(int gravity) {
        getWindow().setGravity(gravity);
        return this;
    }

    /**
     * 设置窗口的显示和隐藏动画
     */
    public void setAnimations(int resId) {
        getWindow().setWindowAnimations(resId);
    }
}