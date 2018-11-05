package com.bst.mallh5.payment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "pay.wxpay")
@Component
@Data
public class Configure {

	public static String KEY;

	public String placeOrderUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";//微信统一下单接口

	public String trackOrderUrl = "https://api.mch.weixin.qq.com/pay/orderquery"; // 查询支付结果
	
	public String notifyUrl; //微信支付回调地址

	public String appId;//微信公众号appid(益教育)

	public String appSecret; //微信公众号密钥
	
	public String getCodeUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APP_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect";//微信获取codeurl
	
	public String getAccessUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APP_ID&secret=APP_SECRET&code=USE_CODE&grant_type=authorization_code";//获取用户openid地址
	
	public String getUserInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPEN_ID&lang=zh_CN";

	public String key;//支付密钥（益教育）

	public String payId;//商户id(益教育)

	public void setKey(String key) {
		this.key = key;
		Configure.KEY = key;
	}
}
