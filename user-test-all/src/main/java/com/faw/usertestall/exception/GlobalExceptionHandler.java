package com.faw.usertestall.exception;

import com.faw.usertestall.domain.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeExceptionHandle(RuntimeException e) {
        logger.error("捕捉到运行时异常：", e);
        return Result.fail(e);
    }

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Result throwableHandle(Exception e) {
        logger.error("捕捉到Throwable异常：", e);
        return Result.fail(e);
    }
}