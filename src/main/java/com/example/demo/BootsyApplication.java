package com.example.demo;

import com.example.demo.bean.Person;
import com.example.demo.bean.PersonTs;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.example.demo.dao")//将项目中对应的mapper类的路径加进来就可以了
public class BootsyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootsyApplication.class, args);
	}

//	@Value("${name}")
//	private String name;

	@Autowired
	public PersonTs personTs;

	@RequestMapping(value = "/a",method = RequestMethod.GET)
	public String say(){
		return "姓名:"+personTs.getName()+"年龄;"+personTs.getAge();
	}
}
