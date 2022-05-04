package cn.bdqn.springbootdemo.controller;

import cn.bdqn.springbootdemo.bean.User;
import cn.bdqn.springbootdemo.service.UserService;
import cn.bdqn.springbootdemo.util.Constants;
import cn.bdqn.springbootdemo.util.JWTUtil;
import cn.bdqn.springbootdemo.util.ResponseData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-22 11:10
 * @since JDK 1.8
 */
//@CrossOrigin
@RestController
@Api("登录")
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;

    @Value("${token.timeout}")
    private Integer timeout;

    @ApiOperation("登录处理")
    @PostMapping("/login")
    public ResponseData login(@ApiParam("用户名") String userName, @ApiParam("密码")String userPwd, HttpSession session, HttpServletResponse response){
        //服务器端验证...
        User user = this.userService.queryByName(userName);
        if(null==user){
            return  new ResponseData("error","用户不存在");
        }
        if(!user.getUserPwd().equals(userPwd)){
            return  new ResponseData("error","用户名或密码不正确");
        }

        String token = JWTUtil.createToken(JSONObject.toJSONString(user));
        //session.setAttribute("LOGIN",user);
        //把token保存到缓存中（想成是一个map.put(token,token)）
        redisTemplate.opsForValue().set(token,token,timeout*2, TimeUnit.MINUTES);
        JSONObject data = new JSONObject();
        data.put("token",token);
        return new ResponseData("success","登录成功!",data);
    }
}
