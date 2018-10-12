package com.demo.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.demo.domain.User;

//用户仓库
//相当于一个用户的数据库
@Repository
public class UserRepository {
	//考虑到高并发情况，使用ConcurrentMap作为存储容器
	//这里采用了内存型的存储方式--map
	private final ConcurrentMap<Integer, User>repository
	=new ConcurrentHashMap<>();
	
	//使用AtomicInteger实现id的自增长
	private final static AtomicInteger idGenerator
	=new AtomicInteger();
	
	//创建这个仓库用来保存一个user对象的方法
	public boolean save(User user) {
		//create并且get id
		Integer id=idGenerator.incrementAndGet();
		user.setId(id);
		//若put成功，则返回null
		return repository.put(id,user)==null;
	}
}
