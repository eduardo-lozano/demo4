package com.demo.eduardo.demo4.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component   // Managed by Spring
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
		for(SomeUser someUser: users) {
			if(someUser.getId() == id) {
				return someUser;
			}
		}
		return null;
	}
	
	public SomeUser deleteSomeUserById(int id) {
		Iterator<SomeUser> iterator = users.iterator();
		while(iterator.hasNext()) {
			SomeUser user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
