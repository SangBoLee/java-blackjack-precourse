package domain.user;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {	
	private final List<User> userList;
	
	public UserRepository(List<User> userList) {
		this.userList = userList;
	}
	
	public String getUserNamse() {
		return userList.stream()
				.map(user -> user.toName().toString())
				.collect(Collectors.joining(", "));
	}
	
	public String getFirstCardResult() {
		return userList.stream()
				.map(user -> user.toName() + " 카드 : " + user.toFirstCardResult() + "\n")
				.collect(Collectors.joining());
	}
	
	public String getAllResult() {
		return userList.stream()
				.map(user -> user.toName().toString() + " 카드 : " 
						+ user.toCards().toCardResult() + " - 결과 : " 
						+ user.toCards().toScore().toInteger() + "\n")
				.collect(Collectors.joining());
	}
	
	public List<User> getUserList() {
		return userList;
	}
}