package com.faw.usertestall.exception;

import com.faw.usertestall.domain.common.ErrorCode;

/**
 * 业务异常
 *
 * @author 鹿胜宝
 * @date 2023/03/22
 */
public class BusinessException extends RuntimeException {
    private final String code;

    /**
     * 根据枚举来构建业务异常
     *
     * @param errorCode 错误代码
     * @author 鹿胜宝
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getCode();
    }

    /**
     * 根据自定义消息构建业务异常
     *
     * @param errorCode 错误代码
     * @param message   消息
     * @author 鹿胜宝
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    /**
     * 根据异常构建业务异常
     *
     * @param errorCode 错误代码
     * @param throwable throwable
     * @author 鹿胜宝
     */
    public BusinessException(ErrorCode errorCode, Throwable throwable) {
        super(throwable);
        this.code = errorCode.getCode();
    }
}
