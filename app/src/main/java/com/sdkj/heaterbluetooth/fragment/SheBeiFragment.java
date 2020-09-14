package com.sdkj.heaterbluetooth.fragment;

import android.os.Bundle;
import android.view.View;

import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.basicmvp.BaseFragment;

public class SheBeiFragment extends BaseFragment {
    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_kongzhiye;
    }

    @Override
    protected void initView(View rootView) {

    }

    public static SheBeiFragment newInstance() {
        Bundle args = new Bundle();
        SheBeiFragment fragment = new SheBeiFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
