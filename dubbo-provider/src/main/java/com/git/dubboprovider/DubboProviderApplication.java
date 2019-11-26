package com.git.dubboprovider;

import com.git.config.condition.MyComponentScan;
import com.google.errorprone.annotations.MustBeClosed;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@DubboComponentScan({"com.git.dubboprovider.service"})
@MyComponentScan
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
