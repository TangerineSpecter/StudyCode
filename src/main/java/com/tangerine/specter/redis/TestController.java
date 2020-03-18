package com.tangerine.specter.redis;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class TestController {
    @Resource
    private RedisAnnoServiceDemo redisAnnoServiceDemo;

    public void test() {

    }
}
