package cn.bdqn.springbootdemo.util;

import lombok.Data;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-22 11:10
 * @since JDK 1.8
 */
@Data

public class ResponseData {
    private String code;
    private String message;
    private Object data;

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResponseData(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
