package fr.insa.soap.dao;

import java.util.HashMap;

import fr.insa.soap.User;

public class UserDAO {
	
	private static HashMap<Integer, User> users;

	private static void initUsers() {
		if(users==null) {
			users = new HashMap<>();
		}
	}
	
	public static int addUser(User user) {
		initUsers();
		final int res;
		if(!users.containsKey(user.getId())) {
			users.put(user.getId(), user);
			res=0;
		} else {
			res=-1;
		}
		return res;
	}

	public static User[] getUsers() {
		return users==null? null:users.values().toArray(User[]::new);
	}

	public static int setValidator(int user, int validator) {
		final int res;
		if(users.containsKey(user)&&users.containsKey(validator)) {
			users.get(user).setValidator(validator);
			res= 0;
		} else {
			res =-1;
		}
		return res;
	}
}
