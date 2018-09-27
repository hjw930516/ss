package com.huz.hjw.service;

import com.huz.hjw.bean.Person;
import com.huz.hjw.bean.Result;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by hasee on 2018/4/1.
 */
public interface PersonService {
    Result search();

    Result getOne(Integer id);

    Result insert(Person person,BindingResult bindingResult) throws Exception;

    Result update(Person person);

    Result delete(Integer id);
}
