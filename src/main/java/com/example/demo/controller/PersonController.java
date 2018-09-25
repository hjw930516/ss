package com.example.demo.controller;

import com.example.demo.bean.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hasee on 2018/4/1.
 */
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/search")
    public List<Person> search(){
        return personService.search();
    }

    @GetMapping("/get/{id}")
    public Person get(@PathVariable Integer id){
        return personService.getOne(id);
    }

    @PostMapping("/insert")
    public String insert(Person person){
        return personService.insert(person);
    }

    @PostMapping("/update")
    public String update(Person person){
        return personService.update(person);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return personService.delete(id);
    }
}
