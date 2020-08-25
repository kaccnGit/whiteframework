package com.whiteBox.core.element;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: kaccn
 * @description:白盒测试请求执行元素
 * @create: 2020-08-25 16:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActElement {

    /**
     * 执行次数
     */
    private int actNums;

    /**
     * 测试参数
     */
    private String params;

}
