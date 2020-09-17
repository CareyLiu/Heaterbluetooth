package com.sdkj.heaterbluetooth.view.inter;

import android.view.View;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface OnItemClickListener {

    void onClick(View view, int position);

    void onItemClick(View view, int indexProvince, int indexCity, int indexArea);

}
