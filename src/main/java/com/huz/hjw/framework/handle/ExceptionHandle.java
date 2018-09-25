package com.huz.hjw.framework.handle;

import com.huz.hjw.bean.Result;
import com.huz.hjw.framework.exception.CommonException;
import com.huz.hjw.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hasee on 2018/4/6.
 * 异常拦截器，将异常的结果放入自定义的json格式中去
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof CommonException){
            CommonException commonException = (CommonException)e;
            return ResultUtils.error(commonException.getCode(),commonException.getMessage());
        }
        logger.error("error={}",e);
        return ResultUtils.error(-1,"未知错误");
    }
}
