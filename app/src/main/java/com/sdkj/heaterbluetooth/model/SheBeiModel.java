package com.sdkj.heaterbluetooth.model;

import com.chad.library.adapter.base.entity.SectionEntity;

public class SheBeiModel extends SectionEntity {
    public SheBeiModel(boolean isHeader, String header) {
        super(isHeader, header);
    }
    /**
     * ccid : aaaaaaaaaaaaaaaa10090018
     * validity_term : 使用中
     * validity_state : 1
     * validity_time : 2021-08-31 17:11:14
     * device_img_url : https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11709
     * device_name : 风暖加热器
     */

    public String ccid;
    public String validity_term;
    public String validity_state;
    public String validity_time;
    public String device_img_url;
    public String device_name;
    public String device_type;


}
