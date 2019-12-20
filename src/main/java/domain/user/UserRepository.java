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
					.map(user -> user.getName().toString())
					.collect(Collectors.joining(", "));
	}
	
	public String getFirstCardResult() {
		return userList.stream()
					.map(user -> user.getName() + " 카드 : " + user.getFirstCardResult() + "\n")
					.collect(Collectors.joining());
	}
	
	public String getAllResult() {
		return userList.stream()
					.map(user -> user.getName().toString() + " 카드 : " 
							+ user.getCards().getCardResult() + " - 결과 : " 
							+ user.getCards().getScore().toInteger() + "\n")
					.collect(Collectors.joining());
	}
	
	public List<User> getUserList() {
		return userList;
	}
}