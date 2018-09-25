package com;


import com.github.pagehelper.PageHelper;
import com.huz.hjw.BootsyApplication;
import com.huz.hjw.bean.User;
import com.huz.hjw.dao.RedisMapper;
import com.huz.hjw.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootsyApplication.class)
public class redisTest {
    private final static Logger logger = LoggerFactory.getLogger(redisTest.class);
    @Autowired
    private RedisMapper redisMapper;
    @Autowired
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        redisMapper.setVal("name","hjw");
        logger.info(redisMapper.getVal("name").trim());

        logger.info("===========================--------------------========================");
        PageHelper.startPage(1, 10);
        List<User> blackList=userMapper.selectAllUser();
        redisTemplate.opsForValue().set("blacklist",blackList);
        ValueOperations<String, List<User>> operation = redisTemplate.opsForValue();
        List<User> resultBlackList = operation.get("blacklist");

        logger.info(resultBlackList.toString());
        for(User blacklist:resultBlackList){
            logger.info("account:"+blacklist.getAccount()+" password:"+blacklist.getPassword());
        }
        logger.info("===========================--------------------========================");
    }

    @Test
    public void testSet() {
        Set<String> set1 = new HashSet<String>();
        set1.add("set1");
        set1.add("set2");
        set1.add("set3");
        redisTemplate.opsForSet().add("set1", set1);
        Set<Object> resultSet = redisTemplate.opsForSet().members("set1");
        Iterator it = resultSet.iterator();
        while (it.hasNext()) {
            logger.info("resultSet:" + it.next());
            logger.info("=================------------===================");
        }

    }

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        redisTemplate.opsForHash().putAll("map1", map);
        Map<Object, Object> resultMap = redisTemplate.opsForHash().entries("map1");
        List<Object> reslutMapList = redisTemplate.opsForHash().values("map1");
        Set<Object> resultMapSet = redisTemplate.opsForHash().keys("map1");
        String value = (String) redisTemplate.opsForHash().get("map1", "key1");
        logger.info("value:" + value);
        logger.info("resultMapSet:" + resultMapSet);
        logger.info("resultMap:" + resultMap);
        logger.info("resulreslutMapListtMap:" + reslutMapList);
    }
    @Test
    public void testList(){
        List<String> list1=new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

        List<String> list2=new ArrayList<String>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        redisTemplate.opsForList().leftPush("listkey1",list1);
        redisTemplate.opsForList().rightPush("listkey2",list2);
        List<String> resultList1=(List<String>)redisTemplate.opsForList().leftPop("listkey1");
        List<String> resultList2=(List<String>)redisTemplate.opsForList().rightPop("listkey2");
        logger.info("resultList1:"+resultList1);
        logger.info("resultList2:"+resultList2);
    }
}
