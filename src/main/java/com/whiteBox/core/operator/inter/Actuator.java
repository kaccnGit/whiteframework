package com.whiteBox.core.operator.inter;

import com.whiteBox.core.element.ActElement;

/**
 * @author: kaccn
 * @description: 白盒测试的执行器
 * @create: 2020-08-17 23:34
 **/
public interface Actuator {

    /**
     * 执行目标方法
     *
     * @return
     * @throws Exception
     */
    void doWhiteMethod() throws Exception;


    /**
     * 执行目标方法
     *
     * @param element
     * @throws Exception
     */
    void doWhiteMethod(ActElement element) throws Exception;

}
