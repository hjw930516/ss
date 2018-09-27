package com.huz.hjw.service;

import com.huz.hjw.bean.Result;
import com.huz.hjw.bean.User;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface UserService {
    Result deleteByPrimaryKey(Integer id);

    Result insert(User record,BindingResult bindingResult);

    Result insertSelective(User record);

    Result selectByPrimaryKey(Integer id);

    Result updateByPrimaryKeySelective(User record);

    Result updateByPrimaryKey(User record);

    Result selectAllUser(int pageNum, int pageSize);
}
