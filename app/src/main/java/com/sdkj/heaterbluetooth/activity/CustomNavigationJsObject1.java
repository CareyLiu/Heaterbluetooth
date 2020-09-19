package com.sdkj.heaterbluetooth.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sdkj.heaterbluetooth.app.PreferenceHelper;
import com.sdkj.heaterbluetooth.common.UIHelper;
import com.sdkj.heaterbluetooth.model.GaoDeMapModel;
import com.sdkj.heaterbluetooth.util.AppUtils;
import com.sdkj.heaterbluetooth.util.OpenLocalMapUtil;


import static com.sdkj.heaterbluetooth.app.App.JINGDU;
import static com.sdkj.heaterbluetooth.app.App.WEIDU;


/**
 * Created by czb365 on 2018/8/16.
 */

public class CustomNavigationJsObject1 {
    private Activity activity;
    private String key, value;

    public CustomNavigationJsObject1(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return 返回数据给前端
     * @JavascriptInterface 这个注解必须添加，否则js调不到这个方法
     * 这个方法名称也必须要和前端保持一致
     */
    @JavascriptInterface
    public void jsToAppMap(String para) {
        //去做想做的事情。比如导航，直接带着开始和结束的经纬度Intent到导航activity就可以

        Log.i("jsToAppMap", para);
//        if (TextUtils.isEmpty(startLat) || TextUtils.isEmpty(startLng) || TextUtils.isEmpty(endLat)
//                || TextUtils.isEmpty(endLng)) {//如果接收的数据不正确，给予提示
//            Toast.makeText(activity, "有不正确的数据", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle("提示");
//        builder.setMessage("请调用自己的导航\n开始经纬度:" +
//                startLat + "    " + startLng +
//                "\n结束经纬度:" + endLat + "    " + endLng);
//
//        builder.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        builder.setCancelable(false);
//        builder.show();
        GaoDeMapModel gaoDeMapModel = new Gson().fromJson(para, GaoDeMapModel.class);

        Double latitude = gaoDeMapModel.getGps_x();
        Double longitude = gaoDeMapModel.getGps_y();

        //起点
        String qiDianLo = PreferenceHelper.getInstance(activity).getString(JINGDU, "0X11");
        String qiDianLa = PreferenceHelper.getInstance(activity).getString(WEIDU, "0X11");


        //判断是否安装了 百度地图
        boolean baiDuFlag = AppUtils.isAvilible(activity, "com.baidu.BaiduMap");
        boolean gaoDeFlag = AppUtils.isAvilible(activity, "com.autonavi.minimap");
        if (baiDuFlag) {
            UIHelper.ToastMessage(activity, "即将为您打开百度地图", Toast.LENGTH_SHORT);
            if (OpenLocalMapUtil.isBaiduMapInstalled()) {
                try {
                    String uri = OpenLocalMapUtil.getBaiduMapUri(String.valueOf(qiDianLa), String.valueOf(qiDianLo), "当前位置",
                            String.valueOf(latitude), String.valueOf(longitude), gaoDeMapModel.getGps_address(), "", "聚易佳");
                    Intent intent = Intent.parseUri(uri, 0);
                    activity.startActivity(intent); //启动调用

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (gaoDeFlag) {
            UIHelper.ToastMessage(activity, "即将为您打开高德地图", Toast.LENGTH_SHORT);
            String uri = OpenLocalMapUtil.getGdMapUri("聚易佳", String.valueOf(qiDianLa), String.valueOf(qiDianLo),
                    "当前位置", String.valueOf(latitude), String.valueOf(longitude), gaoDeMapModel.getGps_address());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.autonavi.minimap");
            intent.setData(Uri.parse(uri));
            activity.startActivity(intent); //启动调用
        } else {

            if (gaoDeFlag) {
                UIHelper.ToastMessage(activity, "系统无法获取您的当前位置，无法使用导航功能", Toast.LENGTH_SHORT);
                return;
            }

            if (baiDuFlag) {
                UIHelper.ToastMessage(activity, "系统无法获取您的当前位置，无法使用导航功能", Toast.LENGTH_SHORT);
                return;
            }
            UIHelper.ToastMessage(activity, "请安装百度或高德地图后再进行尝试", Toast.LENGTH_SHORT);
        }
    }


    //拿到设置webView的属性
    @JavascriptInterface
    public void setExtraInfoHead(String key, String value) {
        setKey(key);
        setValue(value);
        Log.e("添加头信息", key + "," + value);
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

