package com.huz.hjw.controller;

import com.huz.hjw.bean.Person;
import com.huz.hjw.bean.Result;
import com.huz.hjw.service.PersonService;
import com.huz.hjw.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by hasee on 2018/4/1.
 */
@RestController()
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/search")
    public Result search(){
        return personService.search();
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable Integer id){
        return personService.getOne(id);
    }

    @PostMapping("/insert")
    public Result insert(@Valid Person person,BindingResult bindingResult) throws Exception{
        return personService.insert(person,bindingResult);
    }

    @PostMapping("/update")
    public Result update(Person person){
        return personService.update(person);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return personService.delete(id);
    }
}
