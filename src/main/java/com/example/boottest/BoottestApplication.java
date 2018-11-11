package com.example.boottest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 远程Debug需要用到jpda技术
 * 对于tomcat
 * 一、在windows系统中：
 * 打开%CATALINE_HOME%/bin下的文件catalina.bat，加入下面这行：
 * set CATALINA_OPTS=-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address= 8000
 * 其中 address=8000是没被使用的端口号。连接方式有两种，为dt_shmem和dt_socket，分别表示本机调试和远程调试。
 * 二、在非windows系统中：
 * linux下，打开%CATALINE_HOME%/bin下的文件catalina.sh，
 * if [ -z "$JPDA_ADDRESS" ]; then
 * JPDA_ADDRESS="localhost:8000"
 * 改为
 * JPDA_ADDRESS="端口号"
 * 还需要把% CATALINE_HOME %/bin/startup.sh中的最后一行exec "PRGDIR"/"EXECUTABLE" start "$@" 中的start改成jpda start。由于默认的端口是8000，所以如果8000端口已有他用的话，还需在catalina.sh文件中设置：JPDA_ADDRESS=8000。
 * 输入命令startup.sh或者catalina.sh jpda start就可启动tomcat。
 * 对于jar包
 * java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 xxx.jar
 * 最后netstat -anp | grep 端口号 可以通过端口看远程debug是否启用
 * <p>
 * 关于nginx 命令
 * 启动 nginx
 * 关闭 nginx -s stop
 * 重启 nginx -s reload
 * 查看编译参数 nginx -V
 * 配置反向代理需要关闭selinux setenforce 0
 * mac 下的配置文件在/usr/local/etc/nginx
 * 配置监控信息
 * location = /nginx_status {
 * stub_status on;
 * access_log off;
 * allow 127.0.0.1;
 * deny all;
 * }
 * 打开配置文件打开log日志。
 * log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
 * '$status $body_bytes_sent "$http_referer" '
 * '"$http_user_agent" "$http_x_forwarded_for"';
 * <p>
 * access_log  /Users/yibo/Logs/access.log  main;
 * 安装python2 安装pip,然后pip install ngxtop
 * 执行 ngxtop -c nginx配置文件 -g remote_addr 查看访问ip地址
 * -i 'status==200' 执行状态过滤
 * <p>
 * 关于JVM
 * 大对象直接进入老年代
 * 可以设置参数来规定何为大对象
 * -XX:PretenureSizeThreshold
 * 长期存活对象进入老年代
 * -XX:MaxTenuringThreshold
 * -XX:+PrintTenuringDistribution
 * -XX:TargetSurvivorRatio
 * -XX:MaxGCPauseMills 设置最大停顿时间
 * -XX:GCTimeRatio=<n> 垃圾收集时间占1/1+n;
 * -XX:+UseSerialGC -XX:+UseSerialOldGC 使用串行GC
 * Parallel Server模式默认GC
 *
 * @author yibo
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.boottest.dao")
@ServletComponentScan
public class BoottestApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BoottestApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BoottestApplication.class, args);
    }
}