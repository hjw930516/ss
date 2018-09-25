package com.huz.hjw.utils;

import com.huz.hjw.bean.Result;

/**
 * 返回的json格式
 * Created by hasee on 2018/4/6.
 */
public class ResultUtils {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("success");
        result.setSuccess(true);
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setSuccess(false);
        return result;
    }
}
