package com.sdkj.heaterbluetooth.fragment;

import android.os.Bundle;
import android.view.View;

import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.basicmvp.BaseFragment;

public class ShuoMingFragment extends BaseFragment {
    @Override
    protected void initLogic() {
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_shuoming;
    }
    @Override
    protected void initView(View rootView) {

    }

    public static ShuoMingFragment newInstance() {
        Bundle args = new Bundle();
        ShuoMingFragment fragment = new ShuoMingFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
