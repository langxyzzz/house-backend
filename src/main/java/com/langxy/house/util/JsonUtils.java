package com.langxy.house.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String STANDARD_FORMAT = "yyyy/MM/dd HH:mm:ss";

    static {
        //序列化时包括所有字段
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换日期为时间戳的设置
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);

        //忽略空bean转json的报错，默认情况下 empty bean 会报错
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //格式化时间日期
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));

        //忽略在json中存在属性却在bean无对应属性转换时报错
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转Json格式字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String  obj2string(T obj){
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("parse obj2string warn",e);
            return null;
        }
    }

    /**
     * 格式化json字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String  obj2stringPretty(T obj){
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("parse obj2string warn",e);
            return null;
        }
    }

    /**
     * 转换单个对象，无法正确处理集合
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T string2obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T)str :  OBJECT_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("parse string2obj warn",e);
            return null;
        }
    }

    /**
     * 通过 TypeReference 指定要返回的集合对象
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2obj(String str, TypeReference<T> typeReference){
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class) ? str : OBJECT_MAPPER.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("parse string2obj warn",e);
            return null;
        }
    }

    /**
     * 通过传入多个class对象来进行类型转换
     * @param str
     * @param collectionClass
     * @param classes
     * @param <T>
     * @return
     */
    public static <T> T string2obj(String str, Class<?> collectionClass,Class<?>... classes){
        if (StringUtils.isEmpty(str) || collectionClass == null || classes==null) {
            return null;
        }
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, classes);
        try {
            return OBJECT_MAPPER.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("parse string2obj warn",e);
            return null;
        }
    }

}

