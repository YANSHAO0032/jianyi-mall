package com.jianyi.mall.exception;

/**
 * @Author: YANSHAO
 * @Description: 统一异常
 * @Date: 2021/2/3 14:17
 */
public class JianyiMallException extends RuntimeException {
    private final Integer code;
    private final String message;

    public JianyiMallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JianyiMallException(JianyiMallExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
