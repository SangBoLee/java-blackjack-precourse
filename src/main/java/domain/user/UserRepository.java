package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.game.Game;
import domain.view.ViewInput;
import domain.view.ViewOutput;

public class UserRepository {
	private final int playerFirstInx = 1;
	private final int dealerInx = 0;
	private final int Jack = 21;
	
	private List<String> playerNameList = new ArrayList<String>();
	private List<User> userList = new ArrayList<User>();
	private List<Double> profit = new ArrayList<Double>();
			
	public void makePlayerName(String name) {
		String[] names = name.split(",", -1);
		
		for (String player : names) {
			playerNameList.add(ViewInput.getPlayerName(player.trim()));
		}
		
		System.out.println();
	}
	
	public boolean checkPlayerNameList() {
		boolean flag = true;
		
		for (String player : playerNameList) {
			flag = wrongInput(flag, player);
		}
		
		if (!flag) {
			playerNameList.removeAll(playerNameList);
			return false;
		}
		return true;
	}
	
	public boolean wrongInput(boolean flag, String player) {
		if(player.length() != 0 && flag) {
			return true;
		}
		return false;
	}
	
	public void makeUserList() {
		Dealer dealer = new Dealer();
		userList.add(dealer);
		
		for (String name : playerNameList) {
			Player player = new Player(name, ViewInput.getBettingPrice(name));
			
			userList.add(player);
		}
		
		System.out.println();
	}
	
	public List<User> getUserList() {
		return userList;
	}
	
	public String getPlayerNames() {
		String playerNames = playerNameList
				.stream()
				.collect(Collectors.joining(", "));
		
		return playerNames;
	}
	
	public void showFirstUserCard() {
		for (User user : userList) {
			ViewOutput.showFristCardResult(user);
			user.showFirstResult();
		}
	}
	
	public void secondPlayerDealOut() {
		for (int i = playerFirstInx; i < userList.size(); i++) {
			User user = userList.get(i);
			checkAnswer(user);
		}
	}
	
	public void checkAnswer(User user) {
		String answer = "y";
		
		while (answer.equals("y") && ((Player)user).isBelowJack()) {
			answer = ViewInput.askGetCard((Player)user);
			ViewOutput.showEachResult(user);
			System.out.println();
		}
	}
	
	public void secondDealerDealOut() {
		Dealer dealer = (Dealer)userList.get(dealerInx);
		
		while (dealer.isBelowCriteria()) {
			dealer.addCard(Game.getInstance().selectedCard());
			ViewOutput.showDealerCheck();
		}
	}
	
	public void showAllResult() {
		for (User user : userList) {
			ViewOutput.showEachResult(user);
			ViewOutput.showScoreResult(user);
		}
		System.out.println();
	}
	
	public void makeProfitResult() {
		int criteria = userList.get(dealerInx).getScore();
		
		for (int i = 0; i < userList.size(); i++) {
			profit.add((double)0);
		}
		
		for (int i = playerFirstInx; i < userList.size(); i++) {
			compareResult(i, criteria);
		}
	}
	
	public void compareResult(int playerInx, int criteria) {
		if (!isDraw(playerInx, criteria)) {
			modifyProfit(getWinnerInx(playerInx, criteria), 
					getLoserInx(getWinnerInx(playerInx, criteria), playerInx), 
					((Player)userList.get(playerInx)).getBettingMoney());
		}
	}
	
	public boolean isDraw(int playerInx, int criteria) {
		if (userList.get(playerInx).getScore() == criteria) {
			return true;
		}
		return false;
	}
	
	public void modifyProfit(int winnerInx, int loserInx, double money) {
		profit.set(winnerInx, profit.get(winnerInx) + money);
		profit.set(loserInx, profit.get(loserInx) - money);
	}
	
	public int getWinnerInx(int playerInx, int criteria) {
		int playerScore = userList.get(playerInx).getScore();
		
		if (playerScore <= Jack && (playerScore > criteria || (criteria > Jack))) {
			return playerInx;
		}
		return dealerInx;
	}
	
	public int getLoserInx(int winnerInx, int playerInx) {
		if (winnerInx == playerInx) {
			return dealerInx;
		}
		return playerInx;
	}
	
	public List<Double> getProfit() {
		return profit;
	}
}
