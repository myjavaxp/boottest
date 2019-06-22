package com.example.boottest.config;

import com.example.boottest.thread.local.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(urlPatterns = "/*", filterName = "httpFilter")
@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("请求线程ID:{},请求地址为:{}", Thread.currentThread().getId(), httpServletRequest.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());//这里其实可以放入用户ID
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        log.info("请求处理用时:[{}]毫秒", System.currentTimeMillis() - start);
    }

    @Override
    public void destroy() {

    }
}
