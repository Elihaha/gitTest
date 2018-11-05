package com.bst.user.permission.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET = "xuehuixuexi123";  
   
    /** 
     * 生成token
     */  
    public static  String createToken(String target) {
        try {  
        	Calendar calendar=Calendar.getInstance();
        	calendar.add(Calendar.MINUTE, 5);
        	Date expiresAt=calendar.getTime();
            final Map<String, Object> heads = new HashMap<String, Object>();  
            heads.put("alg"	, "HMAC256");
            heads.put("typ", "jwt");
           String token= JWT.create().withHeader(heads)
        	.withClaim("token",target)
            .withExpiresAt(expiresAt)
            .withIssuedAt(new Date())
            .sign(Algorithm.HMAC256(SECRET));
           return token;
        } catch(Exception e) {  
            return null;  
        }  
    }  
      /**
       * 解析token
       * @param token
       * @return
       */
    public static Map<String,Claim> verifyToken(String token) {  
        try {
        	JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        	DecodedJWT verify = verifier.verify(token);
        	return verify.getClaims();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    } 
    	/**
    	 * 测试代码
    	 */
/*    public static void main(String[] args) {
		String token = createToken("sessionIdasdjslakdjklsadjlkasdlk1232132");
		Map<String, Claim> claims = verifyToken(token);
		System.out.println(claims.get("token").asString());
	}*/
}
