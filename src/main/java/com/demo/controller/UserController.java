package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.User;
import com.demo.repository.UserRepository;

//用户的控制器
//用于处理跟用户的交互
//这里处理的是 通过前端请求的参数信息 创建一个user对象，并将其存储到repository的过程
@RestController
public class UserController {
	private final UserRepository userRepository;
	
	//采用构造器的注入方式将repository注入进来
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	//通过请求参数的方式将user对象的name传入
	@PostMapping("/person/save")
	public User save(@RequestParam String name) {
		User user=new User();
		user.setName(name);
		
		//保存成功，模拟日志打印
		if(userRepository.save(user)) {
			System.out.printf("用户对象：%s 保存成功!\n",user);
		}
		return user;
	}
}
