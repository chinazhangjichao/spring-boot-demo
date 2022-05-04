package cn.bdqn.springbootdemo.service.impl;

import cn.bdqn.springbootdemo.bean.NewsType;
import cn.bdqn.springbootdemo.dao.NewsTypeDao;
import cn.bdqn.springbootdemo.service.NewsTypeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 10:17
 * @since JDK 1.8
 */
@Transactional
@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Resource
    private NewsTypeDao newsTypeDao;

    @Override
    @Cacheable(cacheNames = "NEWSTYPE",key = "'ALL'")
    public List<NewsType> queryAll() {
        return newsTypeDao.queryAll();
    }
}
