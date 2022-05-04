package cn.bdqn.springbootdemo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-25 9:55
 * @since JDK 1.8
 */
@Data
public class NewsType implements Serializable {
    private Integer typeId;
    private String typeName;
}
