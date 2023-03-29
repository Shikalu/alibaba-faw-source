package com.faw.usertestall.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 鹿胜宝
 * @date 2023/03/29
 */
public class UserExportDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 127337111953576715L;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private  String username;

    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 版本
     */
    @ExcelProperty(value = "版本号")
    private Long version;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private LocalDateTime created;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
