package com.bst.mallh5.payment;

/**
 * 微信检查支付状态参数封装类
 */
public class WxCheckedPay {

    //公众账号ID
    private String appid;

    //商户号
    private String mch_id;

    //微信订单号
    private String transaction_id;

    //商户订单号
    private String out_trade_no;

    //随机串
    private String nonce_str;

    //签名
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WxCheckedPay{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
