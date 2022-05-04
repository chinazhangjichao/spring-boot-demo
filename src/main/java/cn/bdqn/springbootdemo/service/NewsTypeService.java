package cn.bdqn.springbootdemo.service;

import cn.bdqn.springbootdemo.bean.NewsType;

import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 10:15
 * @since JDK 1.8
 */
public interface NewsTypeService {

    List<NewsType> queryAll();
}
