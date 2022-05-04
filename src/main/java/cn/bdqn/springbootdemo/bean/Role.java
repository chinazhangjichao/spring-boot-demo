package cn.bdqn.springbootdemo.bean;

import com.sun.istack.internal.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-21 10:27
 * @since JDK 1.8
 */
@Data
public class Role implements Serializable {
    private Integer roleId;
    private String roleName;

    public Role(Integer roleId){
        this.roleId=roleId;
    }
}
