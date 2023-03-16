package com.faw.usertestall.domain.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -2343511854115025628L;

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:dd:ss";

    /**
     * 编码
     */
    private String code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 是否成功
     */
    private Boolean Success;

    /**
     * 数据
     */
    private T data;

    /**
     * 时间戳
     */
    private String timestamp;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMessage(ErrorCode.SUCCESS.getDescription());
        result.setSuccess(true);
        result.setData(data);
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        Result<T> result = new Result<>();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getDescription());
        result.setSuccess(false);
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        return result;
    }

    public static <T> Result<T> fail(Throwable e, T data) {
        Result<T> result = new Result<>();
        result.setCode(ErrorCode.SYSTEM_ERROR.getCode());
        result.setMessage(e == null ? ErrorCode.SYSTEM_ERROR.getDescription() : ExceptionUtils.getRootCauseMessage(e));
        result.setSuccess(false);
        result.setData(data);
        result.setTimestamp(DateFormatUtils.format(new Date(), DATE_PATTERN));
        return result;
    }

    public static <T> Result<T> fail(Throwable e) {
        return fail(e, null);
    }

    public static <T> Result<T> fail() {
        return fail(null, null);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", Success=" + Success +
                ", data=" + data +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
