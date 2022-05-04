package cn.bdqn.springbootdemo.dao;

import cn.bdqn.springbootdemo.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-21 10:49
 * @since JDK 1.8
 */
@Mapper
public interface UserDao {

    public User queryByName(String userName);

    public int save(User obj);
}
