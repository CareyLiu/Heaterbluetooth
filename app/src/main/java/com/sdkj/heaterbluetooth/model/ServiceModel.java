package com.sdkj.heaterbluetooth.model;

import java.util.List;

/**
 * Created by Sl on 2019/7/1.
 */

public class ServiceModel {

    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"sub_user_id":"177","inst_id":"217","sub_user_name":"小岳岳","sub_accid":"wit_sub_177"},{"sub_user_id":"198","inst_id":"217","sub_user_name":"未设置","sub_accid":"wit_sub_198"}]
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
         * sub_user_id : 177
         * inst_id : 217
         * sub_user_name : 小岳岳
         * sub_accid : wit_sub_177
         */

        private String sub_user_id;
        private String inst_id;
        private String sub_user_name;
        private String sub_accid;

        public String getSub_user_id() {
            return sub_user_id;
        }

        public void setSub_user_id(String sub_user_id) {
            this.sub_user_id = sub_user_id;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getSub_user_name() {
            return sub_user_name;
        }

        public void setSub_user_name(String sub_user_name) {
            this.sub_user_name = sub_user_name;
        }

        public String getSub_accid() {
            return sub_accid;
        }

        public void setSub_accid(String sub_accid) {
            this.sub_accid = sub_accid;
        }
    }
}
