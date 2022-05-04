package cn.bdqn.springbootdemo.service.impl;

import cn.bdqn.springbootdemo.bean.User;
import cn.bdqn.springbootdemo.dao.UserDao;
import cn.bdqn.springbootdemo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-21 10:57
 * @since JDK 1.8
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Override
    public User queryByName(String userName) {
        if(null==userName || "".equals(userName))
        return null;
        return userDao.queryByName(userName);
    }

    @Override
    public int save(User obj) {
        if(null==obj) return 0;
        if(null==obj.getUserRole()) return 0;
        userDao.save(obj);
        userDao.save(obj);
        return 1;
    }
}
