package com.ebanma.cloud.user.service.constant;

import com.ebanma.cloud.user.api.dto.UserInfoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 鹿胜宝
 * @date 2023/05/10
 */
public class MockDataConstant {
    public static List<UserInfoDTO> userMockList = new ArrayList<>();
    static {
        UserInfoDTO test1 = new UserInfoDTO(1L, "test1", 18);
        UserInfoDTO test2 = new UserInfoDTO(2L, "test2", 19);
        UserInfoDTO test3 = new UserInfoDTO(3L, "test3", 20);
        userMockList.add(test1);
        userMockList.add(test2);
        userMockList.add(test3);
    }
}
