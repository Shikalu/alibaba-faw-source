package com.ebanma.cloud.user.api;

import com.ebanma.cloud.user.api.dto.UserInfoDTO;
import com.ebanma.cloud.user.api.result.ApiResult;

import java.util.List;

/**
 *
 * @author 鹿胜宝
 * @date 2023/05/10
 */
public interface UserApi {
    /**
     * 保存用户
     *
     * @param userInfoDto 用户信息dto
     * @return {@link ApiResult }<{@link UserInfoDTO }>
     * @author 鹿胜宝
     */
    ApiResult<UserInfoDTO> saveUser(UserInfoDTO userInfoDto);

    /**
     * 获取用户列表
     *
     * @return {@link ApiResult }<{@link List }<{@link UserInfoDTO }>>
     * @author 鹿胜宝
     */
    ApiResult<List<UserInfoDTO>> getUserLists();
}
