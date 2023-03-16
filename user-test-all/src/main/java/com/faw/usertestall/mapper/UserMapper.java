package com.faw.usertestall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.faw.usertestall.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
