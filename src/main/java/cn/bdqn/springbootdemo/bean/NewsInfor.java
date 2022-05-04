package cn.bdqn.springbootdemo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 9:56
 * @since JDK 1.8
 */
@Data
@ApiModel("新闻信息")
public class NewsInfor {
    @ApiModelProperty("主键ID")
    private Integer newsId;
    @ApiModelProperty("新闻标题")
    private String newsTitle;
    @ApiModelProperty("新闻图片")
    private String newsImg;
    @ApiModelProperty("新闻内容")
    private String newsContent;
    @ApiModelProperty("新闻类别")
    private NewsType newsType;
    @ApiModelProperty("发布时间")
    private Date sendTime;
    @ApiModelProperty("发布人")
    private User sendUser;
}
