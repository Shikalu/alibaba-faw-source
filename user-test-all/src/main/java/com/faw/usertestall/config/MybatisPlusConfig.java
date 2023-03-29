package com.faw.usertestall.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatisPlus配置
 *
 * @author 鹿胜宝
 * @date 2023/03/15
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页拦截器
     *
     * @return {@link PaginationInterceptor }
     * @author 鹿胜宝
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        //数据库类型配置
        paginationInterceptor.setDbType(DbType.MYSQL);

        return paginationInterceptor;
    }

    /**
     * 乐观锁拦截器
     *
     * @return {@link OptimisticLockerInterceptor }
     * @author 鹿胜宝
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
