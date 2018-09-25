package com.huz.hjw.service.impl;

import com.huz.hjw.framework.Enum.ownEnum;
import com.huz.hjw.bean.Person;
import com.huz.hjw.bean.Result;
import com.huz.hjw.framework.exception.CommonException;
import com.huz.hjw.respository.PersonRepository;
import com.huz.hjw.service.PersonService;

import com.huz.hjw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

/**
 * Created by hasee on 2018/4/1.
 */
@Service(value = "personService")
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Result search() {
        return ResultUtils.success(personRepository.findAll());
    }

    @Override
    public Result getOne(Integer id) {
        return ResultUtils.success(personRepository.findOne(id));
    }

    @Override
    @Transactional
    public Result insert(Person person,BindingResult bindingResult) throws Exception{
        personRepository.save(person);
        if(bindingResult.hasErrors()){
            return ResultUtils.error(0,bindingResult.getFieldError().getDefaultMessage());
        }
        if(person.getName().equals("hjw")) {
            throw new CommonException(ownEnum.TIP);
        }

        personRepository.save(person);
        return ResultUtils.success();


    }

    @Override
    public Result update(Person person) {
        personRepository.save(person);
        return ResultUtils.success();
    }

    @Override
    public Result delete(Integer id) {
        personRepository.delete(id);
        return ResultUtils.success();
    }


}
