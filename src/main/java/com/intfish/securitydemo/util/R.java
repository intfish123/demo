package com.intfish.securitydemo.util;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@ToString
public class R implements Serializable {
    private Integer code;
    private String msg;
    public Object data;

    public R() { }
    public R(Integer code, String msg) {this.code=code; this.msg = msg; }
    public R(HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.msg = httpStatus.getReasonPhrase();
    }
    public R(HttpStatus httpStatus, String msg){
        this.code = httpStatus.value();
        this.msg = msg;
    }

    public static R ok(){
        return new R(HttpStatus.OK.value(), CommonMsg.SUCCESS_MSG);
    }
    public static R ok(Object data){
        return new R(HttpStatus.OK.value(), CommonMsg.SUCCESS_MSG).setData(data);
    }
    public static R error(){
        return new R(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonMsg.ERROR_MSG);
    }
    public static R error(Integer code, String msg){
        return new R(code, msg);
    }
    public static R error(HttpStatus status, String msg){
        return new R(status, msg);
    }
    public static R error(HttpStatus status){
        return new R(status);
    }
    public static R error(String msg){
        return new R(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }



}
