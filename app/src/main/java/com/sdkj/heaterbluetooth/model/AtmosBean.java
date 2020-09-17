package com.sdkj.heaterbluetooth.model;

import java.util.List;

public class AtmosBean {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"zhu_apc":"066","ccid":"aaaaaaaaaaaaaaaaaaaaaaa9"}]
     */

    private String msg_code;
    private String msg;
    private List<DataBean> data;

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * zhu_apc : 066
         * ccid : aaaaaaaaaaaaaaaaaaaaaaa9
         */

        private String zhu_apc;
        private String ccid;

        public String getZhu_apc() {
            return zhu_apc;
        }

        public void setZhu_apc(String zhu_apc) {
            this.zhu_apc = zhu_apc;
        }

        public String getCcid() {
            return ccid;
        }

        public void setCcid(String ccid) {
            this.ccid = ccid;
        }
    }
}
