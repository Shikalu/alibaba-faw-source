package com.faw.usertestall.exception;

import com.faw.usertestall.domain.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 *
 * @author 鹿胜宝
 * @date 2023/03/15
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常处理
     *
     * @param e e
     * @return {@link Result }
     * @author 鹿胜宝
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Result businessExceptionHandle(BusinessException e) {
        logger.error("捕捉到业务异常：", e);
        return Result.fail(e);
    }

    /**
     * 运行时异常处理
     *
     * @param e e
     * @return {@link Result }
     * @author 鹿胜宝
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public Result runtimeExceptionHandle(RuntimeException e) {
        logger.error("捕捉到运行时异常：", e);
        return Result.fail(e);
    }

    /**
     * 其它异常处理
     *
     * @param e e
     * @return {@link Result }
     * @author 鹿胜宝
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Result throwableHandle(Exception e) {
        logger.error("捕捉到Throwable异常：", e);
        return Result.fail(e);
    }
}
