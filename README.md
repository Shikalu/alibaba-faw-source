## alibaba-faw-source
### 框架源码学习

- 新建SpringBoot项目
  - 选择模块
    - Spring Boot DevTools
    - Spring Configuration Processor
    - Spring Web
  - 修改 SpringBoot 版本号为：2.4.2
----
### 包结构
- com.ebanma.cloud.usertestall
  - config
  - controller
  - domain
    - entity
    - dto
    - vo
    - common
  - exception
  - filter
  - interceptor
  - mapper
  - service
  - util
----
### 常用工具类
``` xml
<!-- 常用工具类 -->  
<dependency>  
   <groupId>org.apache.commons</groupId>  
   <artifactId>commons-lang3</artifactId>  
   <version>3.12.0</version>  
</dependency>
```
### 集成MyBatis

```xml
<!-- MySql驱动 -->  
<dependency>  
   <groupId>mysql</groupId>  
   <artifactId>mysql-connector-java</artifactId>  
   <scope>runtime</scope>  
</dependency>  
<!-- mybatis支持 -->  
<dependency>  
   <groupId>org.mybatis.spring.boot</groupId>  
   <artifactId>mybatis-spring-boot-starter</artifactId>  
   <version>2.1.1</version>  
   <exclusions>  
      <exclusion>  
         <artifactId>mybatis</artifactId>  
         <groupId>org.mybatis</groupId>  
      </exclusion>  
   </exclusions>  
</dependency>  
<!-- 指定mybatis版本 -->  
<dependency>  
   <groupId>org.mybatis</groupId>  
   <artifactId>mybatis</artifactId>  
   <version>3.5.0-SNAPSHOT</version>  
</dependency>
```
### 自动生成DAO
```bash
java -jar lib/mybatis-generator-core-1.3.5.jar -configfile generatorConfig.xml -overwrite
```
### 集成MyBatis Plus
1. 在pom.xml文件引入相关的jar包依赖
```xml
<!-- mybatis plus支持 -->  
<dependency>  
   <groupId>com.baomidou</groupId>  
   <artifactId>mybatis-plus-boot-starter</artifactId>  
   <version>3.3.1</version>  
</dependency>
```
2. 实现XxxMapper接口。通过此接口来操作数据持久化
3. 对XxxDO实体进行注解的定义，如：数据库表名，字段的定义，乐观锁
   - @TableName("tb_user")
   - @TableId(type = IdType.ASSIGN_ID)
   - @Version
4. 如需修改Plus默认配置，需实现MybatisPlusConfig类，如分页拦截器及乐观锁等
5. 如需要自定义一个方法，需实现XxxMapper.xml，来定义自定义SQL

### 配置数据连接信息
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

### 自动更新系统及字段
1. 新建service测试类 UserServiceImplTest
2. 公共元数据处理器
3. 为XxxDO配置注解 @TableField(fill  = FieldFill.INSERT) 
4. 配置乐观锁（对应实体类有@Version字段，且配置中添加乐观锁拦截器） 
   - 如果更新数据中不带有version字段：不使用乐观锁，并且version不会累加
   - 如果更新字段中带有version，但与数据库中不一致，更新失败
   - 如果带有version，并且与数据库中一致，更新成功，并且version会累加

### 统一异常处理功能
1. 实现一个异常处理的类，并且用 @ControllerAdvice  修饰
2. 新增业务异常 BusinessException

### 集成验证框架
1. 在pom.xml引入相关的jar包支持
 ```xml
<!-- 验证框架支持 -->  
<dependency>  
   <groupId>org.springframework.boot</groupId>  
   <artifactId>spring-boot-starter-validation</artifactId>  
</dependency>
```
2. 在待验证的实体里面添加相应的注解，根据实际字段需要可以建立不同分组
3. 在Controller中添加相应的注解 @Validated（类及方法入参）注意：@NotEmpty用在集合类上面 @NotBlank 用在String上面 @NotNull 用在基本类型上
4. 做手动参数校验工具类，完成service层的参数校验 ValidatorUtils

#### JCP和JSP
- JCP：Java Community Process ，Java社区进程，成立于1998年，官网，由社会各界Java组成的社区，规划和领导Java的发展，其成员可以在这里看到。
- JSR：Java Specification Requests，Java规范请求，由JCP成员向委员会提交的Java发展议案，经过一系列流程后，如果通过最终会体现在未来的Java中。

#### 常见 JSR 及参考实现举例
| JSR              | 代表API                                     | 主流实现                | 简介           |
| ---------------- | ------------------------------------------- | ----------------------- | -------------- |
| JSR 315、JSR 369 | Servlet、Filter                             | Tomcat、Jetty           | 处理Http请求   |
| JSR 303、JSR 380 | Validator、[@Valid ](/Valid )               |                         |                |
|                  | Hibernate Validator                         | Bean Validation数据校验 |                |
| JSR 330          | @Inject、[@Singleton ](/Singleton )         |                         |                |
|                  | Spring、Guice                               | DI依赖注入              |                |
| JSR 318、JSR 345 | @EJB、EntityContext                         | Glassfish               | 企业级Bean     |
| JSR 317、JSR 907 | @Entity、@ManyToMany、[@Column ](/Column )  |                         |                |
|                  | Hibernate                                   | JPA数据持久化           |                |
| JSR 250          | @PostConstruct、[@Resource ](/Resource )    |                         |                |
|                  | Spring                                      | Common通用注解          |                |
| JSR 914、JSR 343 | @JMSProducer、MessageConsumer               | ActiveMQ、Artemis       | JMS MQ消息队列 |
| JSR 907          | TransactionManager                          | atomikos                | JTA分布式事务  |
| JSR 919          | @MailSessionDefinition                      | JavaMail                | Java邮件       |
| JSR 3            | MBeanServer、[@ManagedBean ](/ManagedBean ) |                         |                |
|                  | Java SE                                     | JMX扩展                 |                |
| JSR 221          | Connection、Statement                       | MySql、Oracle           | JDBC规范       |

#### validation介绍

Validation 在Java中运用最早在2009 年，Java 官方提出了 Bean Validation 规范，而后经历了JSR303、JSR349、JSR380 三次标准的更迭，发展到了 2.0 。

Bean Validation 只提供规范，不提供具体的实现。因此实际使用过程，常用的是 Hibernate validation 和 Spring Validator 校验机制。

-  @Valid  注解，是 Bean Validation 所定义，主要是使用 Hibernate validation 校验机制的时候使用；可以添加在普通方法、构造方法、方法参数、方法返回、成员变量上，表示它们需要进行约束校验。因为可以添加在成员变量，所以支持嵌套校验。
-  @Validated  注解，是 Spring Validation 所定义，主要是使用 Spring Validator 校验机制的时候使用；可以添加在类、方法参数、普通方法上，表示它们需要进行约束校验。同时，@Validated  有 value 属性，支持分组校验。

#### 常见的注解

在javax.validation.constraints 包下，定义了一系列的约束（constraint）注解，一共 22 个注解。

##### 空和非空检查

- @NotBlank：只能用于字符串不为 null ，并且字符串 .trim() 以后 length 要大于 0 。
- @NotEmpty：集合对象的元素不为 0 ，即集合不为空 。
- @NotNull：不能为 null 。
- @Null：必须为 null 。

##### 数值检查

- @DecimalMax(value)：被注释的元素必须是一个数字，其值必须小于等于指定的最大值。
- @DecimalMin(value)：被注释的元素必须是一个数字，其值必须大于等于指定的最小值。
- @Digits(integer, fraction)：被注释的元素必须是一个数字，其值必须在可接受的范围内。
- @Positive：判断正数。
- @PositiveOrZero：判断正数或 0 。
- @Max(value)：该字段的值只能小于或等于该值。
- @Min(value)：该字段的值只能大于或等于该值。
- @Negative：判断负数。
- @NegativeOrZero：判断负数或 0 。

##### Boolean 值检查

- @AssertFalse：被注释的元素必须为 true 。
- @AssertTrue：被注释的元素必须为 false 。

##### 长度检查

- @Size(max, min)：检查字段的 size 是否在 min 和 max 之间，可以是字符串、数组、集合、Map 等。
- `@Length(max, min)` ，完整包名是 `org.hibernate.validator.constraints.Length`
  验证实体类中 **String 类型** 属性的长度是否在给定的范围之内。

##### 日期检查

- @Future：被注释的元素必须是一个将来的日期。
- @FutureOrPresent：判断日期是否是将来或现在日期。
- @Past：检查该字段的日期是在过去。
- @PastOrPresent：判断日期是否是过去或现在日期。

##### 其它检查

- @Email：被注释的元素必须是电子邮箱地址。
- @Pattern(value)：被注释的元素必须符合指定的正则表达式。

### 集成缓存框架
1. 相关注解
   - @Cacheable  : 缓存数据，一般用在查询方法上。将查询到的数据进行缓存
   - @CachePut  : 更新方法上，将数据从缓存中进行更新
   - @CacheEvict  : 删除缓存
2. pom.xml 引入CaffeineCache相关的jar包支持
    - 最常见的本地缓存是 Guava 和 Caffeine， 而 Caffeine 是基于 Google Guava Cache 设计经验改进的结果，相较于 Guava 在性能和命中率上更具有效率，你可以认为其是 Guava Plus。相关数据对比 https://zhuanlan.zhihu.com/p/161541335
```xml
<!-- 缓存框架支持 -->
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-cache</artifactId>
</dependency> 
```
3. CacheManager Bean 配置类
4. 使用注解，标识我们的方法哪些需要缓存

### 实现全局限流功能
1. 先pom.xml引入Guava工具包的支持
```xml
<!-- Guava支持 -->  
<dependency>  
   <groupId>com.google.guava</groupId>  
   <artifactId>guava</artifactId>  
   <version>28.0-jre</version>  
</dependency>
```
2. 定义一个拦截器，实现令牌的发放和获取
3. 将拦截器配置到web系统中

### 使用TraceId实现日志跟踪
1. 建立一个过滤器，在过滤器中给线程设置TraceId。注意在SpringBootApplication上使用@ServletComponentScan。 使用后，Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
2. 将日志配置文件进行修改，把TraceId打印到日志中

```properties
# properties文件设置
logging.pattern.console=%clr(%d{${LOG_DATEFORMAT_PATTERN:yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr([%X{traceId}]){yellow} %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n
```

### 文件上传下载
1. 文件上传的Controller，负责处理文件上传
2. 文件上传的服务接口，通过接口的形式来定义出文件上传的功能
3. 实现文件上传业务逻辑
4. 文件下载，采用静态路径映射的方式实现

```properties
# 不限制大小
spring.servlet.multipart.max-file-size=-1
```
### Eecel导出功能
1. pom.xml把相关的jar配置好
```xml
<!-- EasyExcel相关支持 -->  
<dependency>  
   <groupId>com.alibaba</groupId>  
   <artifactId>easyexcel</artifactId>  
   <version>3.2.1</version>  
</dependency>
```
2. UserController新增加数据导出方法
3. ExcelService新增数据导出服务
4. 实现数据查询并导出到excel中
   a. 需要创建一个EasyExcel导出对象
   b. 分页查询加载数据
   c. 导出分页数据到excel中
   d. 关闭excel文件流
5. 实现excel文件上传

### 服务异步化
1. 先创建线程池 ThreadPoolConfig
2. 开启异步功能 @EnableAsync
3. 将导出方法使用 @Async 注解标记为异步执行







