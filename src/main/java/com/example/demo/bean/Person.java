package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hasee on 2018/3/28.
 */
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private int age;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
