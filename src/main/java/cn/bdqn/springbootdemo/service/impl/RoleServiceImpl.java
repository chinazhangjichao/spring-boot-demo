package cn.bdqn.springbootdemo.service.impl;

import cn.bdqn.springbootdemo.bean.Role;
import cn.bdqn.springbootdemo.dao.RoleDao;
import cn.bdqn.springbootdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-21 10:55
 * @since JDK 1.8
 */
@Service
@Transactional
public class RoleServiceImpl  implements RoleService{
    @Resource
    private RoleDao roleDao;
    @Override
    public List<Role> queryAll() {
        return roleDao.queryAll();
    }
}
