package cn.bdqn.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-29 10:07
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value")String value){
        System.out.println("key:"+key);
        System.out.println("value:"+value);
        redisTemplate.opsForValue().set(key,value);
        return "success";
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key){
       return  (String)redisTemplate.opsForValue().get(key);
    }

}
