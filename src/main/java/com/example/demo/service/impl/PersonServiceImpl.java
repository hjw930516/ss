package com.example.demo.service.impl;

import com.example.demo.bean.Person;
import com.example.demo.respository.PersonRepository;
import com.example.demo.service.PersonService;

import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by hasee on 2018/4/1.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> search() {
        return personRepository.findAll();
    }

    @Override
    public Person getOne(Integer id) {
        return personRepository.findOne(id);
    }

    @Override
    public String insert(Person person) {
        personRepository.save(person);
        return null;
    }

    @Override
    public String update(Person person) {
        personRepository.save(person);
        return null;
    }

    @Override
    public String delete(Integer id) {
        personRepository.delete(id);
        return null;
    }

}
