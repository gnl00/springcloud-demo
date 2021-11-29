package com.boot.cloud.common.resp;

import lombok.*;

import java.io.Serializable;

/**
 * Result<T>
 * @author lgn
 */

@NoArgsConstructor
@Builder
public class Result<T> implements Serializable {

    private Boolean flag;
    private Integer code;
    private T data;

    public Result(Boolean flag, Integer code) {
        this.flag = flag;
        this.code = code;
    }

    public Result(Boolean flag, Integer code, T data) {
        this.flag = flag;
        this.code = code;
        this.data = data;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
