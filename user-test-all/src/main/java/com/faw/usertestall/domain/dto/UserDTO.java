package com.faw.usertestall.domain.dto;

import com.faw.usertestall.util.InsertValidationGroup;
import com.faw.usertestall.util.UpdateValidationGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6728497964686236776L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = InsertValidationGroup.class)
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码不能为空", groups = InsertValidationGroup.class)
    @Length(min = 6, max = 20, message = "密码长度不得少于6位，不得超过20位")
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message = "用户密码不能为空", groups = InsertValidationGroup.class)
    @Email(message = "必须为有效邮箱格式")
    private String email;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空", groups = InsertValidationGroup.class)
    @Max(value = 100, message = "年龄不能大于100岁")
    @Min(value = 10, message = "年龄不能小于10岁")
    private Integer age;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空", groups = InsertValidationGroup.class)
    private String phone;

    /**
     * 版本号
     */
    @NotNull(message = "版本号不能为空", groups = UpdateValidationGroup.class)
    private Long version;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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