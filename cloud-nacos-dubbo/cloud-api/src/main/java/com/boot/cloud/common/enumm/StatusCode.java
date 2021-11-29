package com.boot.cloud.common.enumm;

/**
 * StatusCode
 *
 * @author lgn
 */

public enum StatusCode {


    SUCCESS(200, "success"),
    NOT_FOUND(404, "page not found"),
    SERVICE_ERROR(500, "service error");

    public static Integer SUCCESS_CODE = SUCCESS.getCode();
    public static Integer NOT_FOUND_CODE = NOT_FOUND.getCode();
    public static Integer SERVICE_ERROR_CODE = SERVICE_ERROR.getCode();

    private Integer code;
    private String msg;

    StatusCode() {
    }

    StatusCode(Integer code, String msg) {
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
