package com.sdkj.heaterbluetooth.model;

import com.google.gson.annotations.SerializedName;

public class SaoMaWeiXinPayModel {
    /**
     * type : 2
     * pay : {"package":"Sign=WXPay","out_trade_no":"2018040320313603409","appid":"wxafadbee6859e4228","sign":"2DFF66AF7A6A13A17C33B09FD61815B5","partnerid":"1501279761","prepayid":"wx11104357788192822803dfe71566889800","noncestr":"1589165037819","timestamp":"1589165037"}
     */

    private String type;
    private PayBean pay;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public static class PayBean {
        /**
         * package : Sign=WXPay
         * out_trade_no : 2018040320313603409
         * appid : wxafadbee6859e4228
         * sign : 2DFF66AF7A6A13A17C33B09FD61815B5
         * partnerid : 1501279761
         * prepayid : wx11104357788192822803dfe71566889800
         * noncestr : 1589165037819
         * timestamp : 1589165037
         */

        @SerializedName("package")
        private String packageX;
        private String out_trade_no;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
