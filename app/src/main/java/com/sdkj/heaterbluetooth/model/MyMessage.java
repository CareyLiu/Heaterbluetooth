package com.sdkj.heaterbluetooth.model;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

@MessageTag(value = "SDCustomTypeId", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class MyMessage extends MessageContent implements Serializable {
    private String customTitle;//自定义消息标题    修配厂推荐
    private String customRepairUrl;//自定义消息修配厂图片地址
    private String customRepairDis;// 自定义消息修配厂距离
    private String customRepairName;//自定义消息修配厂名字
    private String addr;//自定义消息修配厂地址
    private String lat_x;// 自定义消息修配厂纬度
    private String lon_y;// 自定义消息修配厂经度


    /*
     *
     * 实现 encode() 方法，该方法的功能是将消息属性封装成 json 串，
     * 再将 json 串转成 byte 数组，该方法会在发消息时调用，如下面示例代码：
     * */
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("customTitle", this.getCustomTitle());
            jsonObj.put("customRepairUrl", this.getCustomRepairUrl());
            jsonObj.put("customRepairDis", this.getCustomRepairDis());
            jsonObj.put("customRepairName", this.getCustomRepairName());
            jsonObj.put("addr", this.getAddr());
            jsonObj.put("lat_x", this.getLat_x());
            jsonObj.put("lon_y", this.getLon_y());
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }

        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 覆盖父类的 MessageContent(byte[] data) 构造方法，该方法将对收到的消息进行解析，
     * 先由 byte 转成 json 字符串，再将 json 中内容取出赋值给消息属性。
     * */
    public MyMessage(byte[] data) {
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("customTitle"))
                setCustomTitle(jsonObj.optString("customTitle"));

            if (jsonObj.has("customRepairUrl"))
                setCustomRepairUrl(jsonObj.optString("customRepairUrl"));

            if (jsonObj.has("customRepairDis"))
                setCustomRepairDis(jsonObj.optString("customRepairDis"));

            if (jsonObj.has("customRepairName"))
                setCustomRepairName(jsonObj.optString("customRepairName"));

            if (jsonObj.has("addr"))
                setAddr(jsonObj.optString("addr"));

            if (jsonObj.has("lat_x"))
                setLat_x(jsonObj.optString("lat_x"));

            if (jsonObj.has("lon_y"))
                setLon_y(jsonObj.optString("lon_y"));
        } catch (JSONException e) {
            Log.d("JSONException", e.getMessage());
        }
    }

    //给消息赋值。
    public MyMessage(Parcel in) {
        //这里可继续增加你消息的属性
        setCustomTitle(ParcelUtils.readFromParcel(in));//该类为工具类，消息属性
        setCustomRepairUrl(ParcelUtils.readFromParcel(in));//该类为工具类，消息属性
        setCustomRepairDis(ParcelUtils.readFromParcel(in));//该类为工具类，消息属性
        setCustomRepairName(ParcelUtils.readFromParcel(in));//该类为工具类，消息属性
        setAddr(ParcelUtils.readFromParcel(in));//该类为工具类，消息属性
        setLat_x(ParcelUtils.readFromParcel(in));//该类为工具类，消息属性
        setLon_y(ParcelUtils.readFromParcel(in));//该类为工具类，消息属性
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<MyMessage> CREATOR = new Creator<MyMessage>() {

        @Override
        public MyMessage createFromParcel(Parcel source) {
            return new MyMessage(source);
        }

        @Override
        public MyMessage[] newArray(int size) {
            return new MyMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将类的数据写入外部提供的 Parcel 中。
     *
     * @param dest  对象被写入的 Parcel。
     * @param flags 对象如何被写入的附加标志。
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, getCustomTitle());
        ParcelUtils.writeToParcel(dest, getCustomRepairUrl());
        ParcelUtils.writeToParcel(dest, getCustomRepairDis());
        ParcelUtils.writeToParcel(dest, getCustomRepairName());
        ParcelUtils.writeToParcel(dest, getAddr());
        ParcelUtils.writeToParcel(dest, getLat_x());
        ParcelUtils.writeToParcel(dest, getLon_y());
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public void setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
    }

    public String getCustomRepairUrl() {
        return customRepairUrl;
    }

    public void setCustomRepairUrl(String customRepairUrl) {
        this.customRepairUrl = customRepairUrl;
    }

    public String getCustomRepairDis() {
        return customRepairDis;
    }

    public void setCustomRepairDis(String customRepairDis) {
        this.customRepairDis = customRepairDis;
    }

    public String getCustomRepairName() {
        return customRepairName;
    }

    public void setCustomRepairName(String customRepairName) {
        this.customRepairName = customRepairName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLat_x() {
        return lat_x;
    }

    public void setLat_x(String lat_x) {
        this.lat_x = lat_x;
    }

    public String getLon_y() {
        return lon_y;
    }

    public void setLon_y(String lon_y) {
        this.lon_y = lon_y;
    }
}