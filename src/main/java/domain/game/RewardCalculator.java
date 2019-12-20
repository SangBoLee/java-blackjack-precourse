package domain.game;

import java.util.ArrayList;
import java.util.List;

import domain.user.Player;
import domain.user.Score;
import domain.user.User;
import domain.user.UserRepository;

public class RewardCalculator {
	private final int dealerInx = 0;
	private final int playerFirstInx = 1;
	private List<User> userList;
	private List<Double> rewardList = new ArrayList<Double>();
	
	public RewardCalculator(UserRepository userRepository) {
		userList = userRepository.getUserList();
	}
	
	public List<Double> makeRewardList() {
		for (int i = 0; i < userList.size(); i++) {
			rewardList.add((double)0);
		}
		
		for (int i = playerFirstInx; i < userList.size(); i++) {
			modifyReward(i);
		}
		
		return rewardList;
	}
	
	public void modifyReward(int playerInx) {
		if (existWinner(playerInx)) {
			int winnerInx = getWinnerInx(playerInx);
			int loserInx = getLoserInx(winnerInx, playerInx);
			double bettingMoney = ((Player)userList.get(playerInx)).bettingMoney().toDouble();
			
			rewardList.set(winnerInx, rewardList.get(winnerInx) + bettingMoney);
			rewardList.set(loserInx, rewardList.get(loserInx) - bettingMoney);
		}
	}
	
	public boolean existWinner(int playerInx) {
		Score dealerScore = userList.get(dealerInx).getCards().getScore();
		Score playerScore = userList.get(playerInx).getCards().getScore();
		
		return (playerScore.isBust() || !playerScore.isDraw(dealerScore) || playerAlwaysWin(playerInx));
	}

	public boolean playerAlwaysWin(int playerInx) {
		return (userList.get(playerInx).getCards().isBlackJack() 
				&& !userList.get(dealerInx).getCards().isBlackJack());
	}
	
	public int getWinnerInx(int playerInx) {
		if (playerWin(playerInx) || playerAlwaysWin(playerInx)) {
			return playerInx;
		}
		return dealerInx;
	}
	
	public boolean playerWin(int playerInx) {
		Score dealerScore = userList.get(dealerInx).getCards().getScore();
		Score playerScore = userList.get(playerInx).getCards().getScore();
		
		return (playerScore.isBelowBlackJack() && (playerScore.Win(dealerScore) || dealerScore.isBust()));
	}
	
	public int getLoserInx(int winnerInx, int playerInx) {
		if (winnerInx == playerInx) {
			return dealerInx;
		}
		return playerInx;
	}
}
