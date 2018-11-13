package com.example.boottest.config;

import com.example.boottest.thread.local.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(urlPatterns = "/*", filterName = "httpFilter")
public class HttpFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LOGGER.info("do filter:{},{}", Thread.currentThread().getId(), httpServletRequest.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());//这里其实可以放入用户ID
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}