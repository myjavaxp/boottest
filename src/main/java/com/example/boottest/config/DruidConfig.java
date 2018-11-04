package com.example.boottest.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个版本的druid已经可以把这些配置写入yml文件了
 *
 * @author Yibo
 */
@Configuration
public class DruidConfig {
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public ServletRegistrationBean<StatViewServlet> servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid/*");
        bean.addInitParameter("loginUsername", username);
        bean.addInitParameter("loginPassword", password);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.addInitParameter("profileEnable", "true");
        return bean;
    }
}