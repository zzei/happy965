package com.zei.happy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData implements Serializable{

    private Integer code;

    private Object object;

    private String msg;

    /**
     * success不带返回对象
     * @return
     */
    public static JsonData buildSuccess(){
        return new JsonData(200,null,"ok");
    }
    /**
     * success带返回对象
     * @param object
     * @return
     */
    public static JsonData buildSuccess(Object object){
        return new JsonData(200,object,"ok");
    }

    /**
     * success不带返回对象，自定义返回消息
     * @param msg
     * @return
     */
    public static JsonData buildSuccess(String msg){
        return new JsonData(200,null,msg);
    }

    /**
     * success带返回对象、自定义返回消息
     * @param object
     * @param msg
     * @return
     */
    public static JsonData buildSuccess(Object object, String msg){
        return new JsonData(200,object,msg);
    }

    /**
     * error
     * @return
     */
    public static JsonData buildError(){
        return new JsonData(500,null,"error");
    }

    /**
     * error 自定义返回错误消息
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg){
        return new JsonData(500,null,msg);
    }

}
