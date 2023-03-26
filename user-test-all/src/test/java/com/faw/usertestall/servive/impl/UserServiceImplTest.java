package com.faw.usertestall.servive.impl;

import com.faw.usertestall.domain.dto.UserDTO;
import com.faw.usertestall.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户Service测试
 *
 * @author 鹿胜宝
 * @date 2023/03/22
 */
@SpringBootTest
public class UserServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserService userService;

    @Test
    void save() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username1");
        userDTO.setAge(1);
        userDTO.setPassword("password1");
        userDTO.setEmail("email1@email.com");
        userDTO.setPhone("phone1");
        int save = userService.save(userDTO);
        logger.info("{}",save);
    }

    @Test
    void update() {
        Long id = 1638442749572284417L;
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password10000");
        userDTO.setAge(1000);
        userDTO.setVersion(1L);

        int update = userService.update(id, userDTO);

        logger.info("{}", update);
    }

    @Test
    void delete() {
        int delete = userService.delete(1638442749572284417L);

        logger.info("{}", delete);
    }
}
