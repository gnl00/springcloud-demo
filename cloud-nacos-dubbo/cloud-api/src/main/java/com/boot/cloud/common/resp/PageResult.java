package com.boot.cloud.common.resp;

import lombok.*;

import java.io.Serializable;

/**
 * PageResult<T>
 * @author lgn
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class PageResult<T> implements Serializable {

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 分页大小
     */
    private Integer pageSize;

    private T data;

}
