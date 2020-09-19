package com.sdkj.heaterbluetooth.model;

import java.util.List;

public class DaiLiShangQianBaoModel {
    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 0
     * data : [{"user_money":"484.42","inst_money_cash":"0.99","pay_num":"18249030297","pay_name":"岳建男","userable_red":"0.00","userName":"南岗区经销商","pwd_pay":"1"}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
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

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_money : 484.42
         * inst_money_cash : 0.99
         * pay_num : 18249030297
         * pay_name : 岳建男
         * userable_red : 0.00
         * userName : 南岗区经销商
         * pwd_pay : 1
         */

        private String user_money;
        private String inst_money_cash;
        private String pay_num;
        private String pay_name;
        private String userable_red;
        private String userName;
        private String pwd_pay;

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getInst_money_cash() {
            return inst_money_cash;
        }

        public void setInst_money_cash(String inst_money_cash) {
            this.inst_money_cash = inst_money_cash;
        }

        public String getPay_num() {
            return pay_num;
        }

        public void setPay_num(String pay_num) {
            this.pay_num = pay_num;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getUserable_red() {
            return userable_red;
        }

        public void setUserable_red(String userable_red) {
            this.userable_red = userable_red;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd_pay() {
            return pwd_pay;
        }

        public void setPwd_pay(String pwd_pay) {
            this.pwd_pay = pwd_pay;
        }
    }
}
