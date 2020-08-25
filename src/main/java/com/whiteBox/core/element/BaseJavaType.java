package com.whiteBox.core.element;

/**
 * @author: kaccn
 * @description: java基本类型
 * @create: 2020-08-18 15:44
 **/
public enum BaseJavaType {

    BYTE("byte", byte.class),
    CHAR("char", char.class),
    SHORT("short", short.class),
    INT("int", int.class),
    LONG("long", long.class),
    DOUBLE("double", double.class),
    FLOAT("float", float.class),
    BOOLEAN("boolean", boolean.class);

    BaseJavaType(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public Class getVal() {
        return clazz;
    }

    //基本变量类型
    private String name;

    //基本变量类型对应的Class
    private Class clazz;

}
