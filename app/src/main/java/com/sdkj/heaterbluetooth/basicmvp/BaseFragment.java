package com.sdkj.heaterbluetooth.basicmvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sdkj.heaterbluetooth.app.Notice;
import com.sdkj.heaterbluetooth.dialog.LordingDialog;
import com.sdkj.heaterbluetooth.util.RxBus;
import com.sdkj.heaterbluetooth.util.RxUtils;


import butterknife.ButterKnife;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment<T extends BasicPresenter, E extends BasicModel> extends BasicFragment<T, E> {

    protected CompositeSubscription _subscriptions = new CompositeSubscription();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (!_subscriptions.isUnsubscribed()) {
            _subscriptions.unsubscribe();
        }
        // ButterKnife.unbind(this);
    }

    /**
     * 注册事件通知
     * @return
     */
    public Observable<Notice> toObservable() {
        return RxBus.getDefault().toObservable(Notice.class);
    }

    /**
     * 发送消息
     */
    public void sendRx(Notice msg) {
        RxBus.getDefault().sendRx(msg);
    }


    public void showProgressDialog() {
        showProgressDialog("");
    }

    private LordingDialog lordingDialog;
    public void showProgressDialog(String msg) {
        if (lordingDialog == null) {
            lordingDialog = new LordingDialog(getContext());
        }
        lordingDialog.setTextMsg(msg);

        if (!lordingDialog.isShowing()) {
            lordingDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (lordingDialog != null) {
            try {
                lordingDialog.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
