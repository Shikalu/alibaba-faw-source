package com.faw.usertestall.controller;

import com.faw.usertestall.domain.common.ErrorCode;
import com.faw.usertestall.domain.common.PageQuery;
import com.faw.usertestall.domain.common.PageResult;
import com.faw.usertestall.domain.common.Result;
import com.faw.usertestall.domain.dto.UserDTO;
import com.faw.usertestall.domain.dto.UserQueryDTO;
import com.faw.usertestall.domain.vo.UserVO;
import com.faw.usertestall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 保存
     *
     * @param userDTO 用户dto
     * @return int
     * @author 鹿胜宝
     */
    @PostMapping
    public Result save(@RequestBody UserDTO userDTO) {
        int save = userService.save(userDTO);
        if (save == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        int update = userService.update(id, userDTO);
        if (update == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Long id) {
        int delete = userService.delete(id);
        if (delete == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @GetMapping
    public Result<PageResult> get(Integer pageNo, Integer pageSize, UserQueryDTO queryDTO) {

        //构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        Optional.ofNullable(pageNo).ifPresent(pageQuery::setPageNo);
        Optional.ofNullable(pageSize).ifPresent(pageQuery::setPageSize);
        pageQuery.setQuery(queryDTO);
        //查询
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);

        //实体转换
        List<UserVO> userVOList = Optional
                .ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);
                    //特殊字段处理
                    userVO.setPassword("******");
                    return userVO;
                })
                .collect(Collectors.toList());

        //封装返回结果
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult,result);
        result.setData(userVOList);
        return Result.success(result);
    }
}
