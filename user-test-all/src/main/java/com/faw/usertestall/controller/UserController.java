package com.faw.usertestall.controller;

import com.faw.usertestall.domain.common.ErrorCode;
import com.faw.usertestall.domain.common.PageQuery;
import com.faw.usertestall.domain.common.PageResult;
import com.faw.usertestall.domain.common.Result;
import com.faw.usertestall.domain.dto.UserDTO;
import com.faw.usertestall.domain.dto.UserQueryDTO;
import com.faw.usertestall.domain.vo.UserVO;
import com.faw.usertestall.service.ExcelService;
import com.faw.usertestall.service.UserService;
import com.faw.usertestall.util.InsertValidationGroup;
import com.faw.usertestall.util.UpdateValidationGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鹿胜宝
 * @date 2023/03/15
 */

@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    UserService userService;
    @Resource
    ExcelService excelService;

    /**
     * 保存
     *
     * @param userDTO 用户dto
     * @return int
     * @author 鹿胜宝
     */
    @CacheEvict(cacheNames = "user-cache")
    @PostMapping
    public Result save(@Validated(InsertValidationGroup.class)
                       @RequestBody UserDTO userDTO) {
        int save = userService.save(userDTO);
        if (save == 1) {
            return Result.success();
        } else {
            return Result.fail(ErrorCode.SYSTEM_ERROR);
        }
    }

    @PutMapping("/{id}")
    public Result update(@NotNull(message = "id不能为空") @PathVariable("id") Long id,
                         @Validated(UpdateValidationGroup.class) @RequestBody UserDTO userDTO) {
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

//    @Cacheable(cacheNames = "user-cache", key = "#root.methodName + '_' + #root.args[0] + #root.args[1]")
    @GetMapping
    public Result<PageResult> get(Integer pageNo, Integer pageSize, UserQueryDTO queryDTO) {

        //构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        Optional.ofNullable(pageNo).ifPresent(pageQuery::setPageNo);
        Optional.ofNullable(pageSize).ifPresent(pageQuery::setPageSize);
        pageQuery.setQuery(queryDTO);
        if (logger.isInfoEnabled()) {
            logger.info("未使用缓存");
        }
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
        BeanUtils.copyProperties(pageResult, result);
        result.setData(userVOList);
        return Result.success(result);
    }

    /**
     * 数据查询并导出excel
     *
     * @param fileName 文件名称
     * @param queryDTO 查询dto
     * @return {@link Result }<{@link Boolean }>
     * @author 鹿胜宝
     */
    @GetMapping("/export")
    public Result<Boolean> export(String fileName, UserQueryDTO queryDTO) {
        excelService.asyncExport(fileName, queryDTO);
        return Result.success(Boolean.TRUE);
    }
}
