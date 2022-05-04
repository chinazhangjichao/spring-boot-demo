package cn.bdqn.springbootdemo.service;

import cn.bdqn.springbootdemo.bean.NewsInfor;
import cn.bdqn.springbootdemo.util.Page;

import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 10:15
 * @since JDK 1.8
 */
public interface NewsInforService {


    public Page<NewsInfor> queryByPage(Integer pageNo, Integer pageSize, Integer type, String keyword);
    public  int queryCount(Integer type, String keyword);
    public  NewsInfor queryById(Integer id);
    public int saveOrUpdate(NewsInfor obj);
    public  int delete(Integer id);

}
