package com.demo.eduardo.demo4.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SomeUserDaoService {
	
	private static List<SomeUser> users = new ArrayList<SomeUser>();
	private static Integer userQty = 3;


	static {
		users.add(new SomeUser(1, "One", new Date()));
		users.add(new SomeUser(2, "Two", new Date()));
		users.add(new SomeUser(3, "Three", new Date()));
	}
	
	public List<SomeUser> findAll() {
		return users;
	}
	
	public SomeUser saveSomeUser(SomeUser someUser) {
		if (someUser.getId() == null) {
			someUser.setId(++userQty);
		}
		users.add(someUser);
		return someUser;
		
	}
	
	public SomeUser findSomeUser(int id) {
		return users.get(id);
	}
	
	public void deleteSomeUser(int id) {
		users.remove(id);
	}
	
}
