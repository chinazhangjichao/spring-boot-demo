package cn.bdqn.springbootdemo.util;

import lombok.Data;

import java.util.List;

/**
 * @author ZJC
 * @decription: 存储分页信息
 * @date: 2022-04-25 10:20
 * @since JDK 1.8
 */
public class Page<T> {

    private Integer pageNo;
    private Integer pageSize;
    private Integer pageTotal;
    private Integer count;
    private List<T> list;

    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = null==pageNo?1:pageNo;
        this.pageSize = null==pageSize?10:pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }


    public Integer getPageTotal() {
        this.pageTotal =this.count%this.pageSize==0?this.count/this.pageSize:this.count+1;
        return pageTotal;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
