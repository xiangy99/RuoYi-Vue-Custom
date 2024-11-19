package com.ruoyi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 启动程序
 *
 * @author xiangy
 */

@SpringBootApplication
public class AdminManageApplication {
    
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AdminManageApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("RuoYi-Vue-Custom启动成功!");
    }
    
}
