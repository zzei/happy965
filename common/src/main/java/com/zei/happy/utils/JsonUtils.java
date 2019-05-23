package com.zei.happy.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * 使用gson处理json 工具类
 */
public class JsonUtils {

    private static Gson gson = new Gson();

    /**
     * 对象转json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){
        return gson.toJson(object);
    }

    /**
     * json字符串转对象
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }

    /**
     * map转对象
     * @param map
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T mapToJson(Map map, Class<T> classOfT){
        return fromJson(toJson(map),classOfT);
    }
}
