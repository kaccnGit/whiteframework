package com.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: kaccn
 * @description:用户实体类，使用lombok替代无参及有参数构造函数和get、set方法
 * @create: 2019-06-11 14:50
 **/
@Data
@NoArgsConstructor  //无参构造
@AllArgsConstructor //有参构造
public class User {
    private int userId;
    private String userName;
    private String sex;
    private String desc;
}
