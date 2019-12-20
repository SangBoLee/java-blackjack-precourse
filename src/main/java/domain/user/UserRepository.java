package domain.user;

import java.util.List;

public class UserRepository {	
	private final List<User> userList;
	
	public UserRepository(List<User> userList) {
		this.userList = userList;
	}
	
	public List<User> getUserList() {
		return userList;
	}
}