package com.example.boottest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
 *
 * @author yibo
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.boottest.dao")
public class BoottestApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BoottestApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BoottestApplication.class, args);
    }
}