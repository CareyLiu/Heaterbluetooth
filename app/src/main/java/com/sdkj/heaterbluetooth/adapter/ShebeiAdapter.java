package com.sdkj.heaterbluetooth.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.model.SheBeiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ShebeiAdapter extends BaseQuickAdapter<SheBeiModel, BaseViewHolder> {

    public ShebeiAdapter(int layoutResId, @Nullable List<SheBeiModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SheBeiModel item) {
        helper.setText(R.id.tv_shebei_name, item.device_name);
        helper.setText(R.id.tv_ccid, "ccid:" + item.ccid);
        helper.setText(R.id.tv_shebei_data, "设备有效期至:" + item.validity_time);
        helper.setText(R.id.tv_zhuangtai, item.validity_term);
        Glide.with(mContext).load(item.device_img_url).into((ImageView) helper.getView(R.id.iv_shebei_icon));
    }
}
