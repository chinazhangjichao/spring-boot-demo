package cn.bdqn.springbootdemo.service;


import cn.bdqn.springbootdemo.bean.User;

import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-03-31 14:40
 * @since JDK 1.8
 */
public interface UserService {

    public User queryByName(String userName);

    public int save(User obj);

 }
