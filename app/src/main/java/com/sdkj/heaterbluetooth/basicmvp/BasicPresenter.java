package com.sdkj.heaterbluetooth.basicmvp;

import android.content.Context;


public abstract class BasicPresenter<T extends BasicView, E extends BasicModel> {
    public Context mContext;
    public E mModel;
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }

    public void onDestroy() {
        mModel.onDestroy();
    }
}
