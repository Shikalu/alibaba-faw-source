package com.ebanma.cloud.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 鹿胜宝
 * @date 2023/05/10
 */
@Data
@AllArgsConstructor
public class UserInfoDTO implements Serializable {
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

}
