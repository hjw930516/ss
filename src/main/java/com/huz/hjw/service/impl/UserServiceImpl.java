package com.huz.hjw.service.impl;

import com.github.pagehelper.PageHelper;
import com.huz.hjw.bean.Result;
import com.huz.hjw.bean.User;
import com.huz.hjw.dao.UserMapper;
import com.huz.hjw.service.UserService;
import com.huz.hjw.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Result deleteByPrimaryKey(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public Result insert(User record,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ResultUtils.error(0, bindingResult.getFieldError().getDefaultMessage());
        }
        userMapper.insert(record);
//        if("hjw".equals(record.getAccount())){
//           throw new CommonException(ownEnum.TEST);
//        }
        return ResultUtils.success();
    }

    @Override
    public Result insertSelective(User record) {
        userMapper.insertSelective(record);
        return ResultUtils.success();
    }

    @Override
    @Cacheable(value="redisCache")
    public Result selectByPrimaryKey(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return ResultUtils.success(user);
    }

    @Override
    public Result updateByPrimaryKeySelective(User record) {
        userMapper.updateByPrimaryKeySelective(record);
        return ResultUtils.success();
    }

    @Override
    public Result updateByPrimaryKey(User record) {
        userMapper.updateByPrimaryKey(record);
        return ResultUtils.success();
    }

    @Override
     /**
      * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
      * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
      * pageNum 开始页数
      * pageSize 每页显示的数据条数
      * */
    @Cacheable(value="redisCache")
    public Result selectAllUser(int pageNum, int pageSize) {
        List<User> userList = userMapper.selectAllUser();
      /*  ValueOperations<String,List<User>> redisOperat = redisTemplate.opsForValue();
        if(redisTemplate.hasKey("UserList")){
            userList = redisOperat.get("UserList");
            logger.info("redis里的缓存");
        }else{
            //将参数传给这个方法就可以实现物理分页了，非常简单。
            PageHelper.startPage(pageNum, pageSize);
            userList = userMapper.selectAllUser();
            redisOperat.set("UserList",userList);
            logger.info("数据库查询");
        }*/

        logger.info("测试热部署");
        return ResultUtils.success(userList);
    }
}
