package com.faw.usertestall.interceptor;

import com.faw.usertestall.domain.common.ErrorCode;
import com.faw.usertestall.exception.BusinessException;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 限流拦截器
 *
 * @author 鹿胜宝
 * @date 2023/03/22
 */
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    /**
     * 速度限制器 （QPS限制为1）
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(1);

    private static final Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!rateLimiter.tryAcquire()) {
            logger.error("系统被限流了");
            throw new BusinessException(ErrorCode.RATE_LIMIT_ERROR);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
