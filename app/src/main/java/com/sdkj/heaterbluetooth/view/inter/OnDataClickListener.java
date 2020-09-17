package com.sdkj.heaterbluetooth.view.inter;

import android.view.View;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface OnDataClickListener {

    void carNumonClick(View view, int firstPos, int secondPos, String num);

    void dataClick(View view, String data);
}
