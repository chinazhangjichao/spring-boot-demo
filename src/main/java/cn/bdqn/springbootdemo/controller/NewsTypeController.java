package cn.bdqn.springbootdemo.controller;

import cn.bdqn.springbootdemo.bean.NewsType;
import cn.bdqn.springbootdemo.service.NewsTypeService;
import cn.bdqn.springbootdemo.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 10:28
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/nt")
@Api("新闻类别接口")
public class NewsTypeController {


    @Resource
    private NewsTypeService newsTypeService;

    @ApiOperation("查询所有类别")
    @GetMapping("/all")
    public ResponseData all(){
        List<NewsType> list = newsTypeService.queryAll();
        if(null==list || list.size()==0){
            return new ResponseData("failure","无数据");
        }else{
            return new ResponseData("success","加载成功",list);
        }
    }

}
