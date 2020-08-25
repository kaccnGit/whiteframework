package com.whiteBox.core.handler;

import com.whiteBox.core.factory.MethodsFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: kaccn
 * @description: 容器启动时，初始化白盒测试工厂
 * @create: 2020-08-25 10:59
 **/
@Component
public class WhiteHandler extends MethodsFactory implements ApplicationContextAware {

    /**
     * 重写setApplicationContext方法，获取IOC容器：ApplicationContext实例
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            //捕捉该包下的标注方法
            doCatch("com.whiteBox");
            //捕捉spring容器环境下相关标注方法
            doCatch(applicationContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

