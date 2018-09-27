package com.huz.hjw;

import com.huz.hjw.bean.PersonTs;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.huz.hjw.dao")//将项目中对应的mapper类的路径加进来就可以了
public class BootsyApplication extends SpringBootServletInitializer {

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

	//放置外部tomcat容器,打包用
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootsyApplication.class);
	}
}
