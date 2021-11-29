package com.boot.cloud.user.domain.query;

import lombok.*;

import java.io.Serializable;

/**
 * UserQuery
 * @author lgn
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserQuery implements Serializable {

    private Long userId;

    private String userAccount;

    private String username;

    private String controllerPort;

    private String servicePort;

}
