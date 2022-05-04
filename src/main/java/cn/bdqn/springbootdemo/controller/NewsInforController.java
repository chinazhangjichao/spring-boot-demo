package cn.bdqn.springbootdemo.controller;

import cn.bdqn.springbootdemo.bean.NewsInfor;
import cn.bdqn.springbootdemo.bean.NewsType;
import cn.bdqn.springbootdemo.bean.User;
import cn.bdqn.springbootdemo.service.NewsInforService;
import cn.bdqn.springbootdemo.service.NewsTypeService;
import cn.bdqn.springbootdemo.util.Page;
import cn.bdqn.springbootdemo.util.ResponseData;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 10:28
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/news")
public class NewsInforController {

    @Resource
    private NewsInforService newsInforService;

    @RequestMapping("/page")
    public ResponseData page(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "10") Integer pageSize,Integer type,String keyword){
        Page<NewsInfor> page= newsInforService.queryByPage(pageNo,pageSize,type,keyword);
        return new ResponseData("success","加载成功",page);
    }

    @GetMapping("/load")
    public ResponseData load(@RequestParam(defaultValue = "0") Integer id){
        NewsInfor obj = this.newsInforService.queryById(id);

        if(null!=obj){
            return new ResponseData("success","加载成功",obj);
        }else{
            return new ResponseData("failure","加载失败");
        }

    }


    @PostMapping("/save")
    public ResponseData save(@RequestBody NewsInfor obj, HttpSession session){
        //服务器验证...
        User user = (User)session.getAttribute("LOGIN");
        obj.setSendUser(user);
        if(this.newsInforService.saveOrUpdate(obj)>0){
            return new ResponseData("success","保存成功");
        }else{
            return new ResponseData("failure","保存失败");
        }

    }

    @PostMapping("/upload")
    public ResponseData upload(MultipartFile file, HttpSession session){

        String rootDir = session.getServletContext().getRealPath("/");
        File uploadDir = new File(rootDir+"/upload");
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        //验证大小
        //验证格式
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String newName = System.currentTimeMillis()+ext;

        try {
            file.transferTo(new File(uploadDir,newName));
            JSONObject data = new JSONObject();
            data.put("fileName",newName);
            return new ResponseData("success","上传成功",data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseData("failure","上传失败");


    }

    @GetMapping("/delete")
    public ResponseData delete(@RequestParam(defaultValue = "0") Integer id){

        if(this.newsInforService.delete(id)>0){
            return new ResponseData("success","删除成功");
        }else{
            return new ResponseData("failure","删除失败");
        }

    }

}
