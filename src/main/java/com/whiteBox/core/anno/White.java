package com.whiteBox.core.anno;

import java.lang.annotation.*;

/**
 * @author kaccn
 * @description 标注支持自动化测试的方法
 * @date create on 2019-3-7 16:53
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface White {
    /**
     * 方法标注名
     *
     * @return
     */
    String value() default "";

    /**
     * 方法是否来自或使用Spring容器对象（默认不使用）
     *
     * @return
     */
    boolean fromIOC() default false;

}
