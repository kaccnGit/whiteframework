package com.whiteBox.core.operator.inter;

/**
 * @author kaccn
 * @description 捕捉器，用于在项目运行期捕获标记为测试的目标方法
 * @date create on 2019-3-7 16:57
 */
public interface Catcher<T> {

    /**
     * 执行捕捉操作
     */
    void doCatch() throws Exception;

    /**
     * 执行捕捉操作(传参数)
     */
    void doCatch(T t) throws Exception;

}
