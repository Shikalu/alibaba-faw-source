package com.faw.usertestall.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * TraceId过滤器
 *
 * @author 鹿胜宝
 * @date 2023/03/22
 */
@WebFilter(urlPatterns = "/*")
@Order(1)
public class TraceIdFilter implements Filter {


    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = servletRequest.getParameter(TRACE_ID);
        //尝试从请求中获取
        if (StringUtils.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put(TRACE_ID, traceId);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
