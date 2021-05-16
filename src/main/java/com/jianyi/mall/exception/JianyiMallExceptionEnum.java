package com.jianyi.mall.exception;

/**
 * @Author: YANSHAO
 * @Description: 异常枚举
 * @Date: 2021/2/3 13:52
 */
public enum JianyiMallExceptionEnum {

    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003,"密码不能少于8位"),
    NAME_EXISTED(10004, "用户名重复"),
    INSERT_FAILED(10005, "插入失败，请重试"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "用户未登录"),
    UPDATE_FAILED(10008, "更新失败"),
    NEED_ADMIN(10009, "无管理员权限"),
    PARA_NOT_NULL(10010, "参数不能为空"),
    CREATE_FAILD(10011, "新增失败"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    DELETE_FALED(10013,"删除失败"),
    MKDIR_FALED(10014, "文件夹创建失败"),
    UPLOAD_FALED(10015, "图片上传失败"),
    NOT_SALE(10016, "商品不可售"),
    NOT_ENOUGH(10017, "商品库存不足"),
    SYSTEM_ERROR(20000, "系统异常");

    /**
     * 异常码
     */
    Integer code;

    /**
     * 异常信息
     */
    String msg;

    JianyiMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
