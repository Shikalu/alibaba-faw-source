package com.faw.usertestall.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 常见元对象处理程序
 *
 * @author 鹿胜宝
 * @date 2023/03/22
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommonMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        //logger.isInfoEnabled()及logger.isDebugEnabled()都需要进行判断，直接调用虽然也会先判断，但是会在判断前拼接字符串，影响执行性能
        if (logger.isInfoEnabled()) {
            logger.info("新建时，开始填充系统字段");
        }

        this.strictInsertFill(metaObject, "created", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "modified", LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "creator", String.class, "TODO从上下文获取当前人");
        this.strictInsertFill(metaObject, "operator", String.class, "TODO从上下文获取当前人");

        this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
        this.strictInsertFill(metaObject, "version", Long.class, 1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (logger.isInfoEnabled()) {
            logger.info("更新时，开始更新系统字段");
        }

        this.strictInsertFill(metaObject, "modified", LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "operator", String.class, "TODO从上下文获取当前人");
    }
}
