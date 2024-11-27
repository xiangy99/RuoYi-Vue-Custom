package com.ruoyi.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 *
 * @author xiangy
 */

@ComponentScan(basePackages = {"com.ruoyi.demo", "com.ruoyi.system"})
@SpringBootApplication
public class AdminManageApplication {
    
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AdminManageApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("RuoYi-Vue-Custom启动成功!");
    }
    
}
