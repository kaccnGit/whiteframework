package com.whiteBox.core.util;

import com.whiteBox.core.element.BaseJavaType;
import com.whiteBox.core.exception.WhiteException;

import java.lang.reflect.Method;

/**
 * @author kaccn
 * @description 基础功能封装
 * @date create on 2020-8-7 17:35
 */
public class AutoSourceUtil {

    /**
     * 返回反射对应的样例初始化值
     *
     * @param clazz 反射对象
     * @return
     * @throws Exception
     */
    public static Object getSampleValue(Class clazz) throws Exception {

        if (clazz == BaseJavaType.BYTE.getVal())
            return 0;
        if (clazz == BaseJavaType.CHAR.getVal())
            return 0;
        if (clazz == BaseJavaType.SHORT.getVal())
            return 0;
        if (clazz == BaseJavaType.INT.getVal())
            return 0;
        if (clazz == BaseJavaType.LONG.getVal())
            return 0;
        if (clazz == BaseJavaType.DOUBLE.getVal())
            return 0;
        if (clazz == BaseJavaType.FLOAT.getVal())
            return 0;
        if (clazz == BaseJavaType.BOOLEAN.getVal())
            return false;
        else return clazz.newInstance();
    }

    /**
     * 返回样例测试对应的基本类型值或对象
     *
     * @param clazz 反射对象
     * @param param 测试方法字符串
     * @return
     */
    public static Object getRealValue(Class clazz, String param) {

        if (clazz == BaseJavaType.BYTE.getVal())
            return Byte.parseByte(param);
        if (clazz == BaseJavaType.CHAR.getVal())
            return param.charAt(0);
        if (clazz == BaseJavaType.SHORT.getVal())
            return Short.parseShort(param);
        if (clazz == BaseJavaType.INT.getVal())
            return Integer.parseInt(param);
        if (clazz == BaseJavaType.LONG.getVal())
            return Long.parseLong(param);
        if (clazz == BaseJavaType.DOUBLE.getVal())
            return Double.parseDouble(param);
        if (clazz == BaseJavaType.FLOAT.getVal())
            return Float.parseFloat(param);
        if (clazz == BaseJavaType.BOOLEAN.getVal())
            return Boolean.parseBoolean(param);
        else return clazz.cast(param);
    }

    /**
     * 反射调用方法
     *
     * @param clazz  反射class对象
     * @param method 反射方法
     * @param args   需要执行的方法参数
     * @param object 方法对象,设置为null时会使用clazz.newInstance()新建对象
     * @return
     * @throws Exception
     */
    public static Object doMethod(Class<?> clazz, Method method, Object[] args, Object object) throws Exception {
        if (method.getParameterTypes().length == 0) {
            //无参方法，args置为空
            args = null;
        }
        return (object == null) ? method.invoke(clazz.newInstance(), args) : method.invoke(object, args);
    }


    /**
     * 对无测试参数的有参方法，构造对应格式的初始入参数组，各参数使用空白默认值
     *
     * @param method 反射的方法
     * @return
     * @throws Exception
     */
    public static Object[] getSampleParams(Method method) throws Exception {
        //获得一个方法参数数组
        Class[] paramTypes = method.getParameterTypes();
        //拼接动态参数
        Object[] args = new Object[paramTypes.length];
        for (int i = 0, j = paramTypes.length; i < j; i++) {
            args[i] = getSampleValue(paramTypes[i]);
        }
        return args;
    }

    /**
     * 根据测试人员输入的参数拼接成对应格式的可执行参数组
     *
     * @param method 反射的方法
     * @param params 手动输入的参数
     * @return
     * @throws Exception
     */
    public static Object[] getRealParams(Method method, String params) throws Exception {

        String[] stringParams = params.split(",");
        Class[] paramTypes = method.getParameterTypes();

        if (paramTypes.length != stringParams.length) {
            throw new WhiteException("测试参数输入有误！");
        }

        //拼接动态参数
        Object[] args = new Object[paramTypes.length];
        for (int i = 0, j = paramTypes.length; i < j; i++) {
            //基本类型参数处理,
            args[i] = getRealValue(paramTypes[i], stringParams[i]);
        }
        return args;
    }

}
