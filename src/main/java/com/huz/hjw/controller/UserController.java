package com.huz.hjw.controller;

import com.huz.hjw.service.AutoConfigurationProcessDemo;
import com.huz.hjw.bean.Result;
import com.huz.hjw.bean.User;
import com.huz.hjw.service.UserService;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Administrator on 2018/4/8.
 */
@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result<User> search(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        return userService.selectAllUser(pageNum,pageSize);
    }

    @PostMapping("/insert")
    public Result<User> insert(@Valid User user,BindingResult bindingResult){
        return userService.insert(user, bindingResult);
    }
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Integer id){
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
