package domain.view;

import java.util.List;

import domain.user.PlayerNameRepository;
import domain.user.User;
import domain.user.UserRepository;

public class ViewOutput {
	public void showFirstResult(PlayerNameRepository playerNameRepository, 
			UserRepository userRepository) {
		String userNames = "딜러와 " + playerNameRepository.playerNames();
		
		System.out.println(userNames + "에게 2장의 카드를 나누었습니다.");
		showFirstUserCard(userRepository.getUserList());
		System.out.println();
	}
	
	public void showFirstUserCard(List<User> userList) {
		for (User user : userList) {
			showFristCardResult(user);
		}
	}
	
	public void showFristCardResult(User user) {
    	System.out.print(user.getName() + " 카드 : ");
    	System.out.println(user.getFirstCardResult());
	}
	
	public void showDealerCheck() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
	}
	
	public void showAllResult(List<User> userList) {
		for (User user : userList) {
			showEachResult(user);
			showScoreResult(user);
		}
		System.out.println();
	}
	
	public void showEachResult(User user) {
		System.out.print(user.getName() + " 카드 : ");
		System.out.print(user.getCards().cardResult());
	}
	
	public void showScoreResult(User user) {
		System.out.println(" - 결과 : " + user.getCards().getScore().toInteger());
	}
	
	public void showAllReward(List<User> userList, List<Double> profit) {
		System.out.println("### 최종 수익");
		
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			System.out.println(user.getName() + " : " + profit.get(i));
		}
	}
}