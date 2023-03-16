package com.faw.usertestall;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.faw.usertestall.domain.entity.gen.User;
import com.faw.usertestall.util.DaoUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * @author 鹿胜宝
 * @date 2023/03/16
 */
public class DaoUtilTest {
    @Test
    public void test1() throws IOException {
        Function<SqlSession,User> function = sqlSession -> sqlSession.selectOne("com.faw.usertestall.mapper.gen.UserMapper.selectByPrimaryKey", 1L);
        User user = DaoUtils.excute(function);
        System.out.println(user);
    }
}
