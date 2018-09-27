package com.huz.hjw.respository;

import com.huz.hjw.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hasee on 2018/4/1.
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
