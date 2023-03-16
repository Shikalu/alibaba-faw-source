package com.faw.usertestall;
  
import java.util.HashMap;
import java.util.List;

import com.faw.usertestall.domain.entity.UserDO;
import com.faw.usertestall.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.test.context.SpringBootTest;  
  
@SpringBootTest  
class UserMapperTest {  
  
    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);  
  
    @Autowired  
    private UserMapper userMapper;
  
    @Test  
    public void find() {  
        HashMap<String, Object> hashMap = new HashMap<>();  
        hashMap.put("username", "username1");  
        List<UserDO> users = userMapper.selectByMap(hashMap);
  
        logger.info("{}", users);  
    }  
  
}