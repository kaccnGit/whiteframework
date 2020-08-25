package com.whiteBox;

import com.whiteBox.core.anno.White;

/**
 * @author: kaccn
 * @description:测试小demo
 * @create: 2020-08-25 14:21
 **/
public class Demo {

    private int result;

    @White("无参方法")
    public int getResult() {
        System.out.println("getResult() has be done");
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @White("有参(一个参数)")
    public void one(String one) {
        System.out.println("method_one：" + one);
    }

    @White("有参(两个参数)")
    public void two(String two, String twos) {
        System.out.println("method_two:" + two + twos);
    }

    @White("有参(不同类型参数)")
    public void three(String two, int hello) {
        System.out.println(two + "method_three:" + hello);
    }

}