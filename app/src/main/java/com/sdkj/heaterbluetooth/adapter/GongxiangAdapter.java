package com.sdkj.heaterbluetooth.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sdkj.heaterbluetooth.R;
import com.sdkj.heaterbluetooth.model.GongxiangModel;
import com.sdkj.heaterbluetooth.model.SheBeiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class GongxiangAdapter extends BaseQuickAdapter<GongxiangModel.DataBean, BaseViewHolder> {

    public GongxiangAdapter(int layoutResId, @Nullable List<GongxiangModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GongxiangModel.DataBean item) {
        helper.setText(R.id.tv_name, item.getUser_name());
        helper.setText(R.id.tv_phone, item.getUser_phone());
    }
}
