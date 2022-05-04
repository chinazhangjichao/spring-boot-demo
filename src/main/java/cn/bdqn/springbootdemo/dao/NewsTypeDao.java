package cn.bdqn.springbootdemo.dao;

import cn.bdqn.springbootdemo.bean.NewsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-21 10:48
 * @since JDK 1.8
 */
@Mapper
public interface NewsTypeDao {

    public List<NewsType> queryAll();
}
