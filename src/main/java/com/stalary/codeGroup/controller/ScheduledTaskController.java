package com.stalary.codeGroup.controller;

import com.stalary.codeGroup.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 定时器任务类
 * Created by jinghongyu on 06/08/2017.
 */
//@SpringBootApplication
@EnableScheduling
@Configuration
//@Component
public class ScheduledTaskController {

    @Resource
    private UserService userService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //测试接口
    @Scheduled(fixedRate = 864000000L)
    private void test() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    }
