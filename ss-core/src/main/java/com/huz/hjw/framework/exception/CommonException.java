package com.huz.hjw.framework.exception;


import com.huz.hjw.framework.Enum.ownEnum;

/**
 * Created by hasee on 2018/4/6.
 * 自定义异常，和ownEnum枚举一起使用维护
 * spring只能回滚runtimeException，不能回滚exception
 */
public class CommonException extends RuntimeException {
    private Integer code;

    public CommonException(ownEnum enums) {
        super(enums.getMsg());
        this.code = enums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
