package com.ykeshtdar.demoP6.configuration;

import org.springframework.context.annotation.*;
import org.springframework.web.filter.*;

@Configuration
public class WebConfiguration {
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }
}
