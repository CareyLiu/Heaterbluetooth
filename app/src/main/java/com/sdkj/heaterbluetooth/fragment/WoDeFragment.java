package com.sdkj.heaterbluetooth.fragment;

import android.os.Bundle;
import android.view.View;

import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.basicmvp.BaseFragment;

public class WoDeFragment extends BaseFragment {
    @Override
    protected void initLogic() {
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.layout_wode;
    }

    @Override
    protected void initView(View rootView) {

    }

    public static WoDeFragment newInstance() {
        Bundle args = new Bundle();
        WoDeFragment fragment = new WoDeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
