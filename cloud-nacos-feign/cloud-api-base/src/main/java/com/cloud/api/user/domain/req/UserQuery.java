package com.cloud.api.user.domain.req;

import lombok.*;

import java.io.Serializable;

/**
 * UserQuery
 *
 * @author lgn
 * @date 2021-11-29 14:13
 */

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class UserQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String account;

    private String proServerPort;

}
