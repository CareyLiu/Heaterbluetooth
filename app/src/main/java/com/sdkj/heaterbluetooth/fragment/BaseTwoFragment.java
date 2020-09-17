package com.sdkj.heaterbluetooth.fragment;

import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.sdkj.heaterbluetooth.basicmvp.BasicFragment;

public abstract class BaseTwoFragment extends BasicFragment {
    protected boolean isVisible;

    @Override
    protected void initLogic() {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .titleBar(toolbar)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (isVisible) {
            onVisible();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        isVisible = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isVisible = false;
    }

    protected void onVisible() {

    }

}
