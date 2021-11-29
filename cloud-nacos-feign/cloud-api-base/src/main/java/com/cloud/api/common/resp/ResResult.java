package com.cloud.api.common.resp;

import lombok.*;

import java.io.Serializable;

/**
 * ResResult
 *
 * @author lgn
 * @date 2021-11-29 15:22
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class ResResult<T> implements Serializable {

    private static final long serialVersionUID = 42L;

    private Boolean status;

    private Integer code;

    private String msg;

    private T data;

}
