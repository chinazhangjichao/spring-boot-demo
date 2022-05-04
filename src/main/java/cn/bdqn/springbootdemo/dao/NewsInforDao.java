package cn.bdqn.springbootdemo.dao;

import cn.bdqn.springbootdemo.bean.NewsInfor;
import cn.bdqn.springbootdemo.bean.NewsType;
import cn.bdqn.springbootdemo.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-21 10:48
 * @since JDK 1.8
 */
@Mapper
public interface NewsInforDao {

    public List<NewsInfor> queryByPage(@Param("start")int start, @Param("pageSize")int pageSize, @Param("type")Integer type, @Param("keyword")String keyword);
    public  int queryCount(@Param("type")Integer type,@Param("keyword")String keyword);
    public NewsInfor queryById(int id);

    public int save(NewsInfor obj);
    public int update(NewsInfor obj);
    public int delete(int id);
}
