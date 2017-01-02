package com.sjg.sys;

import java.util.List;

import com.sjg.rts.service.BetorderService;
import com.sjg.sys.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class JunitTest {
//    @Resource
//    private RedisUtil redisUtil;
    @Autowired
    private BetorderService betorderService;

    @Test
    public void c1() {
        betorderService.select(null);
//        redisUtil.set("tt","fqj");
    }

}
