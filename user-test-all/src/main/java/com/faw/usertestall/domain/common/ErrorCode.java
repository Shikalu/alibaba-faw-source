package com.faw.usertestall.domain.common;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
public enum ErrorCode {
    SUCCESS("00000", "成功"),
    PARAM_ERROR("A0400", "请求参数错误"),
    SYSTEM_ERROR("B0001", "系统执行出错"),
    ;

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述
     */
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
