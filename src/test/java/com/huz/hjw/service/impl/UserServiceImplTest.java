package com.huz.hjw.service.impl;

import com.github.pagehelper.PageHelper;
import com.huz.hjw.BootsyApplication;
import com.huz.hjw.bean.User;
import com.huz.hjw.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

//在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),让测试运行于Spring测试环境 Spring框架在org.springframework.test.annotation 包中提供
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserMapper userMapper;
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
    @Test
    public void selectAllUser() {
        PageHelper.startPage(1, 2);
        List<User> list = userMapper.selectAllUser();
        logger.info("1"+list);
        assertEquals("1","1");

    }

    @Test
    public void redisTest(){
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        logger.info("服务正在运行: "+jedis.ping());

        logger.info("==========================------Redis Java String(字符串) 实例------========================");

        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        logger.info("redis 存储的字符串为: "+ jedis.get("runoobkey"));

        logger.info("==========================------Redis Java List(列表) 实例------========================");
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            logger.info("列表项为: "+list.get(i));
        }

        logger.info("==========================-----Redis Java Keys 实例-------========================");

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            logger.info(key);
        }


    }
}