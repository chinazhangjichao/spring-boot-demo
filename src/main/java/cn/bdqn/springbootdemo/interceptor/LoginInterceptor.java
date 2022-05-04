package cn.bdqn.springbootdemo.interceptor;

import cn.bdqn.springbootdemo.bean.User;
import cn.bdqn.springbootdemo.util.Constants;
import cn.bdqn.springbootdemo.util.JWTUtil;
import cn.bdqn.springbootdemo.util.ResponseData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.security.KeyRep.Type.SECRET;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-26 11:05
 * @since JDK 1.8
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${token.timeout}")
    private Integer timeout;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(null==request.getHeader("token")){
            this.writeJSON(response,new ResponseData("login","请登录"));
            return false;
        }
        String token= request.getHeader("token");
        System.out.println(">>>>>>>>>>>>>>>token:"+token);
        //根据token 去缓存中拿到真正的token
        Object redis=redisTemplate.opsForValue().get(token);
        if(null==redis){
            System.out.println(">>>>>>>>>>>>>>>redis读取失败");
            this.writeJSON(response,new ResponseData("login","登录超时"));
            return  false;
        }
        String redisToken=(String)redis;
        System.out.println("redisToken:"+redisToken);
        //解析token+
        try{
            DecodedJWT jwt = JWTUtil.verifier(redisToken);
            System.out.println("JWT:"+jwt);
            if(null==jwt){
                this.writeJSON(response,new ResponseData("login","验证失败"));
                return  false;
            }

            if(JWTUtil.validExpire(jwt)){
                System.out.println(">>>>>>>>>>>>>>>1超时了");
                //重写缓存中的token，生成一个新的token，map.put(老token，新token);
                this.writeJSON(response,new ResponseData("login","登录超时"));
                return  false;
            }
            Claim claim = JWTUtil.getClaims(jwt,"user");
            if(null==claim){
                System.out.println(">>>>>>>>>>>>>>>token里未携带user信息");
                this.writeJSON(response,new ResponseData("login","验证失败"));
                return  false;
            }

            if(JWTUtil.checkExpire(jwt,timeout)){
                //重写
                redisToken = JWTUtil.createToken(claim.asString());
                redisTemplate.opsForValue().set(token,redisToken,timeout*2, TimeUnit.MINUTES);
            }
        }catch (Exception e){
            e.printStackTrace();
            this.writeJSON(response,new ResponseData("login","token验证失败"));
            return  false;
        }




        return true;
    }


    private void writeJSON(HttpServletResponse response,ResponseData data){
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().write(JSON.toJSONString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
