package com.whiteBox;

import com.whiteBox.core.factory.MethodsFactory;

/**
 * @author kaccn
 * @description 白盒测试处理器
 * @date create on 2019-3-7 20:06
 */
public class DemoTest extends MethodsFactory {

    public static void main(String[] args) throws Exception {
        DemoTest demoTest = new DemoTest();

        //捕捉器捕捉相应包内标记的方法
        demoTest.doCatch("com.whiteBox");

        //执行白盒测试
        demoTest.doWhiteMethod();
    }
}
