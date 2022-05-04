package cn.bdqn.springbootdemo.service.impl;

import cn.bdqn.springbootdemo.bean.NewsInfor;
import cn.bdqn.springbootdemo.dao.NewsInforDao;
import cn.bdqn.springbootdemo.service.NewsInforService;
import cn.bdqn.springbootdemo.util.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 10:19
 * @since JDK 1.8
 */
@Service
@Transactional
public class NewsInforServiceImpl implements NewsInforService {

    @Resource
    private NewsInforDao newsInforDao;

    @Override
    public Page<NewsInfor> queryByPage(Integer pageNo, Integer pageSize, Integer type, String keyword) {
        Page<NewsInfor> page = new Page<NewsInfor>(pageNo,pageSize);
        page.setCount(this.newsInforDao.queryCount(type,keyword));
        page.setList(this.newsInforDao.queryByPage((pageNo-1)*pageSize,pageSize,type,keyword));
        return page;
    }

    @Override
    public int queryCount(Integer type, String keyword) {
        return this.newsInforDao.queryCount(type,keyword);
    }

    @Override
    public NewsInfor queryById(Integer id) {
        if(null==id || id<=0)
        return null;
        return this.newsInforDao.queryById(id);
    }

    @Override
    public int saveOrUpdate(NewsInfor obj) {
        if(null==obj)
        return 0;
        if(null==obj.getNewsId() || obj.getNewsId()<=0){
            return this.newsInforDao.save(obj);
        }else{
            return this.newsInforDao.update(obj);
        }
    }

    @Override
    public int delete(Integer id) {
        if(null==id || id<=0)
        return 0;
        return this.newsInforDao.delete(id);
    }
}
