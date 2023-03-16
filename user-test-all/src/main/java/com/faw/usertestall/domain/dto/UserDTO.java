package com.faw.usertestall.domain.dto;
  
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
    private String username;  
  
    /**  
     * 用户密码  
     */  
    private String password;  
  
    /**  
     * 邮箱  
     */  
    private String email;  
  
    /**  
     * 年龄  
     */  
    private Integer age;  
  
    /**  
     * 手机号  
     */  
    private String phone;  
  
    /**  
     * 版本号  
     */  
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