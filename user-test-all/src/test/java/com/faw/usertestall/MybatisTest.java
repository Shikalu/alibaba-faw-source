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
        //二级缓存测试
        sqlSession.close();  //只有在前一个缓存关闭或提交后，第二个才能用，否则无法从缓存获取查询结果，无法实现共享
        SqlSession sqlSession2 = sqlSessionFactory.openSession();  //二级缓存测试需要不同的sqlsession共享，创建不同的测试
        User user2 = sqlSession2.selectOne("com.faw.usertestall.mapper.gen.UserMapper.selectByPrimaryKey", 1L);
        System.out.println(user2);
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
