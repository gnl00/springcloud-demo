package com.cloud.admin.query;

import lombok.*;

import java.io.Serializable;

/**
 * AdminAuthQuery
 *
 * @author lgn
 * @date 2021-11-30 14:11
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class AdminAuthQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String path;

    private String function;

}
