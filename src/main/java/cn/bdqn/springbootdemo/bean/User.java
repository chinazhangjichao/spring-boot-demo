package cn.bdqn.springbootdemo.bean;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-21 10:43
 * @since JDK 1.8
 */
@Data
public class User implements Serializable {
    private  Integer userId;
    private String userName;
    private String userPwd;
    private Role userRole;
    private String realName;
}
