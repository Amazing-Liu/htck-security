package com.htck.modules.business.enums;

/**
 * 职位状态枚举
 */
public enum PostStateEnum {
    INACTIVE("下架",0),
    ACTIVE("上架",1);

    PostStateEnum(String name,int value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
