package com.example.demo.service;

import com.example.demo.bean.Person;

import java.util.List;

/**
 * Created by hasee on 2018/4/1.
 */
public interface PersonService {
    List<Person> search();

    Person getOne(Integer id);

    String insert(Person person);

    String update(Person person);

    String delete(Integer id);
}
