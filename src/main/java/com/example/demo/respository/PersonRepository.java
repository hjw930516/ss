package com.example.demo.respository;

import com.example.demo.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hasee on 2018/4/1.
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
