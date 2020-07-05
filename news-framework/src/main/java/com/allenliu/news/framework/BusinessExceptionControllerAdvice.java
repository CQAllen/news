package com.allenliu.news.framework;

import com.allenliu.news.framework.enums.ApiResultEnum;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BusinessExceptionControllerAdvice {

    /**
     * 拦截捕捉异常:Exception
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResult crmBusinessExceptionHandler(Exception e) {
        return ApiResult.build(ApiResultEnum.SYSTEM_EXCEPTION);
    }
}