package com.ebanma.cloud.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ebanma.cloud.user.api.UserApi;
import com.ebanma.cloud.user.api.dto.UserInfoDTO;
import com.ebanma.cloud.user.api.result.ApiResult;
import com.ebanma.cloud.user.service.constant.MockDataConstant;

import java.util.List;

/**
 * @author 鹿胜宝
 * @date 2023/05/10
 */
@Service(version = "${dubbo.provider.version}", application = "${dubbo.application.id}")
public class UserServiceImpl implements UserApi {
    @Override
    public ApiResult<UserInfoDTO> saveUser(UserInfoDTO userInfoDto) {
        userInfoDto.setId(MockDataConstant.userMockList.size() +1L);
        MockDataConstant.userMockList.add(userInfoDto);
        return ApiResult.success(userInfoDto);
    }

    @Override
    public ApiResult<List<UserInfoDTO>> getUserLists() {
        return ApiResult.success(MockDataConstant.userMockList);
    }
}
