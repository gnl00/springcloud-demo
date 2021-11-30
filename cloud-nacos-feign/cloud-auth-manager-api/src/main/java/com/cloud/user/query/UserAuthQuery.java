package com.cloud.user.query;

import lombok.*;

import java.io.Serializable;

/**
 * UserAuthQuery
 *
 * @author lgn
 * @date 2021-11-30 14:01
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class UserAuthQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String path;

    private String function;

}
