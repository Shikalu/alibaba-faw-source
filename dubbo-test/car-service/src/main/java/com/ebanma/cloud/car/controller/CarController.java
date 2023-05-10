package com.ebanma.cloud.car.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ebanma.cloud.user.api.UserApi;
import com.ebanma.cloud.user.api.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author 鹿胜宝
 * @date 2023/05/10
 */
@RestController
@RequestMapping("/users")
public class CarController {

    @Reference(version = "${dubbo.provider.version}", application = "${dubbo.application.id}")
    private UserApi userApi;

    @PostMapping
    public Object saveUser(@RequestBody UserInfoDTO userInfoDTO) {
        System.out.println("【消费者服务】》》添加人员" + userInfoDTO);
        return userApi.saveUser(userInfoDTO);
    }

    @GetMapping
    public Object listUser() {
        System.out.println("【消费者服务】》》获取人员列表");
        return userApi.getUserLists();
    }
}
