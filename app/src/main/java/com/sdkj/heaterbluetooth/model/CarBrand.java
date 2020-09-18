package com.sdkj.heaterbluetooth.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sl on 2019/6/17.
 *
 */

public class CarBrand implements Serializable {



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

    public static class DataBean implements Serializable{

        private String initials;

        private List<CcBean> cc;

        public String getInitials() {
            return initials;
        }

        public void setInitials(String initials) {
            this.initials = initials;
        }

        public List<CcBean> getCc() {
            return cc;
        }

        public void setCc(List<CcBean> cc) {
            this.cc = cc;
        }

        public static class CcBean implements Serializable {
            private String brand_id;
            private String brand_name;
            private String pic_url;
            private String brand_id_up;
            private List<ClBean> cl;

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public String getBrand_id_up() {
                return brand_id_up;
            }

            public void setBrand_id_up(String brand_id_up) {
                this.brand_id_up = brand_id_up;
            }

            public List<ClBean> getCl() {
                return cl;
            }

            public void setCl(List<ClBean> cl) {
                this.cl = cl;
            }

            public static class ClBean implements Serializable {
                /**
                 * brand_id : 453
                 * brand_name : 奥迪A3
                 * pic_url : https://shop.hljsdkj.com/commons/images/siye_autoparts/cartype/160_autohomecar__wKgFWFoAKRCAIW4kAAhC9eEHcg0466.jpg
                 * brand_id_up : 168
                 */

                private String brand_id;
                private String brand_name;
                private String pic_url;
                private String brand_id_up;

                public String getBrand_id() {
                    return brand_id;
                }

                public void setBrand_id(String brand_id) {
                    this.brand_id = brand_id;
                }

                public String getBrand_name() {
                    return brand_name;
                }

                public void setBrand_name(String brand_name) {
                    this.brand_name = brand_name;
                }

                public String getPic_url() {
                    return pic_url;
                }

                public void setPic_url(String pic_url) {
                    this.pic_url = pic_url;
                }

                public String getBrand_id_up() {
                    return brand_id_up;
                }

                public void setBrand_id_up(String brand_id_up) {
                    this.brand_id_up = brand_id_up;
                }
            }
        }
    }
}
