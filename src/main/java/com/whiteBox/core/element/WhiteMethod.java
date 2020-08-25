package com.whiteBox.core.element;

import com.whiteBox.core.operator.AbstractProcessor;
import com.whiteBox.core.util.AutoSourceUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author: kaccn
 * @description: 白盒测试的目标方法元素封装
 * @create: 2020-08-18 00:46
 **/
@Data
@NoArgsConstructor  //无参构造
@AllArgsConstructor //有参构造
public class WhiteMethod extends AbstractProcessor {
    /**
     * 类名
     */
    private String className;
    /**
     * 注解注释
     */
    private String notes;
    /**
     * 反射class
     */
    private Class<?> clazz;
    /**
     * 反射方法
     */
    private Method method;

    /**
     * 方法是否来自或使用Spring容器对象
     */
    private boolean fromIOC;

    /**
     * spring容器中的bean对象
     */
    private Object bean;

    @Override
    public void doWhiteMethod(ActElement element) throws Exception {
        excuteMethod(element.getActNums(), element.getParams());
    }

    /**
     * 反射执行方法
     *
     * @param params
     */
    public void excuteMethod(String params) throws Exception {
        System.out.println("======= Test Staring ======>>  [class/bean:" + className + ", method:" + method.getName() + "]");
        //如果测试人员未输入测试参数，使用默认值生成参数组
        Object[] args = (params == null || "".equals(params)) ? AutoSourceUtil.getSampleParams(method) : AutoSourceUtil.getRealParams(method, params);
        //是否使用容器对象执行方法
        if (fromIOC) {
            AutoSourceUtil.doMethod(clazz, method, args, bean);
        } else {
            AutoSourceUtil.doMethod(clazz, method, args, null);
        }
    }

    /**
     * 反射执行方法（多次）
     *
     * @param times 次数
     */
    public void excuteMethod(int times, String params) throws Exception {
        while (times > 0) {
            excuteMethod(params);
            times--;
        }
    }


}
