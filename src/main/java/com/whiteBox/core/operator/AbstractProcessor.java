package com.whiteBox.core.operator;

import com.whiteBox.core.element.ActElement;
import com.whiteBox.core.operator.inter.Actuator;
import com.whiteBox.core.operator.inter.Catcher;

/**
 * @author: kaccn
 * @description: 抽象处理器，实现捕获器和执行器（利用适配器模式，继承该方法后可不必实现不使用的的接口方法，保持代码清爽简洁）
 * @create: 2020-08-24 16:47
 **/
public abstract class AbstractProcessor implements Actuator, Catcher {


    @Override
    public void doWhiteMethod() throws Exception {

    }

    @Override
    public void doWhiteMethod(ActElement element) throws Exception {

    }

    @Override
    public void doCatch() throws Exception {

    }

    @Override
    public void doCatch(Object o) throws Exception {

    }
}
