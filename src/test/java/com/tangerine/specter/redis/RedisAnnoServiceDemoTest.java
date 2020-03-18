package com.tangerine.specter.redis;

import com.tangerine.specter.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("single")
public class RedisAnnoServiceDemoTest {

    @Autowired
    private RedisAnnoServiceDemo redisAnnoServiceDemo;

    @Test
    public void springCacheTest() throws Exception {
        User user = redisAnnoServiceDemo.findUserById(2L);
        System.out.println(user);
    }
}
