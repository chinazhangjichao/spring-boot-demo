package cn.bdqn.springbootdemo.util;

import cn.bdqn.springbootdemo.bean.User;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-29 10:48
 * @since JDK 1.8
 */
public class JWTUtil {
    //JWT的密钥
    public static final String SECRET="awy3r8e0tgip";


    public static String createToken(String user){
        //生成Token
        Map<String,Object> header = new HashMap<String,Object>();
        header.put("alg","HS256");
        header.put("typ","JWT");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,10);
        return JWT.create()
                .withHeader(header)
                .withClaim("user", user)
                .withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解析token
     * @param token
     * @return 如果能正确解析就返回DecodeJWT,否则null
     */
    public static DecodedJWT verifier(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            if(null!=verifier){
                return verifier.verify(token);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 验证是否超时
     * @param jwt
     * @return true表示已经过期，false表示未过期
     */
    public static boolean validExpire(DecodedJWT jwt){
        Date expiresAt = jwt.getExpiresAt();
        if(expiresAt.getTime()<System.currentTimeMillis()){
            return  true;
        }
        return false;
    }


    /**
     * 检查是否重置
     * @param jwt
     * @return
     */
    public static boolean checkExpire(DecodedJWT jwt,Integer timeout){
        Date expiresAt = jwt.getExpiresAt();
        if(expiresAt.getTime()-System.currentTimeMillis()<=timeout*60000L){
            return  true;
        }
        return false;
    }

    public static Claim getClaims(DecodedJWT jwt,String key){
        return  jwt.getClaim(key);
    }

}
