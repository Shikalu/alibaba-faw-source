package com.faw.usertestall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.faw.usertestall.domain.common.PageQuery;
import com.faw.usertestall.domain.common.PageResult;
import com.faw.usertestall.domain.dto.UserDTO;
import com.faw.usertestall.domain.dto.UserQueryDTO;
import com.faw.usertestall.domain.entity.UserDO;
import com.faw.usertestall.mapper.UserMapper;
import com.faw.usertestall.service.UserService;
import com.faw.usertestall.util.ValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        return userMapper.insert(userDO);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(id);
        return userMapper.updateById(userDO);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult query(PageQuery<UserQueryDTO> pageQuery) {

        ValidationUtil.validate(pageQuery);

        Page page = new Page(pageQuery.getPageNo(), pageQuery.getPageSize());

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(), userDO);
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(userDO);

        IPage<UserDO> userPage = userMapper.selectPage(page, queryWrapper);

        PageResult pageResult = new PageResult();
        pageResult.setTotal(userPage.getTotal());
        pageResult.setPageNo((int) userPage.getCurrent());
        pageResult.setPageNum(userPage.getPages());
        pageResult.setPageSize((int) userPage.getSize());

        List<UserDTO> userDTOS = Optional
                .ofNullable(userPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());
        pageResult.setData(userDTOS);
        return pageResult;
    }
}
