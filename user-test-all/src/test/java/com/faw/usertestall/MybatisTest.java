package com.faw.usertestall;

import com.faw.usertestall.domain.entity.gen.User;
import com.faw.usertestall.domain.entity.gen.UserExample;
import com.faw.usertestall.mapper.gen.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
public class MybatisTest {

    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("com/faw/sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("com.faw.usertestall.mapper.gen.UserMapper.selectByPrimaryKey", 1L);
        System.out.println(user);
    }

    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("com/faw/sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.selectByExample(new UserExample());
        for (User user : all) {
            System.out.println(user);
        }
    }
}
