package com.faw.usertestall.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * @author 鹿胜宝
 * @date 2023/03/16
 */
public class DaoUtils {
    private static SqlSessionFactory factory;

    static {
        String resource = "com/faw/sqlMapConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.err.println("读取sqlMapConfig文件失败");
            e.printStackTrace();
            System.exit(1);
        }
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static <R> R excute(Function<SqlSession, R> function) {
        SqlSession sqlSession = factory.openSession();
        try {
            R apply = function.apply(sqlSession);
            sqlSession.commit();
            return apply;
        } catch (Throwable throwable) {
            sqlSession.rollback();
            System.out.println("执行错误");
            throw throwable;
        } finally {
            sqlSession.close();
        }
    }
}
