package com.sdkj.heaterbluetooth.model;

public class SaoMaZhiFuBaoPayModel {
    /**
     * type : 1
     * pay : app_id=2019041363858556&biz_content=%7B%22out_trade_no%22%3A%222018040320313603410%22%2C%22total_amount%22%3A%221%22%2C%22subject%22%3A%22%E7%9B%B4%E6%8E%A5%E4%B8%8B%E5%8D%95%22%2C%22timeout_express%22%3A%225m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22body%22%3A%22%E9%BA%A6%E6%AF%94%E5%85%8B%E7%81%AB%E9%94%85%28%E7%9B%B4%E6%8E%A5%E4%B8%8B%E5%8D%95%29%22%2C%22seller_id%22%3A%22%22%7D&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fshop.hljsdkj.com%2Fmsg%2Fpay%2FalipayCallback&sign_type=RSA2&timestamp=2020-05-11%2010%3A51%3A46&version=1.0&sign=t%2FcS8eNIxaVEDnpGDqIdtzliliFrbSf76up3Pt7ZXuQEWQtz3LdNXzFw5jCZJa1QkilXrYj9kZj6lWTsV7x%2FPNbvfTgGL8vdFnh14Is8F49hMKecTlQ8WyBU0FzQ8%2FBDbKpuFMRWKUy3qm%2BHYIlW0R9L%2B6t%2Fasaj5LtpteqnX6qgVRtv8Jq1UWGdrmcGFUTc6UTMPsqICdb1AMr92k6ltEodXeQOe0FXRrJCKs%2FztSlckXKH00i0D1LzivaJop4HfYpdFx35NLTRKl9fzjBCCoBBlFiC6kLCyCncZ3SKmzAN%2BeoL8mDcFevWRnawuOMZ6Zaz0jQ8EwospX5PhV%2BB7g%3D%3D
     */

    private String type;
    private String pay;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
