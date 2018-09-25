package com.huz.hjw.framework.Enum;

/**
 * Created by hasee on 2018/4/6.
 */
public enum ownEnum {
    UNKOWN(-1,"未知错误"),
    TIP(-100,"名字不能是这样"),
    SUCCESS(0,"成功"),
    TEST(2,"测试回滚");
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ownEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
