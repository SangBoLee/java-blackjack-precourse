package domain.game;

import domain.user.RewardRepository;
import domain.user.UserRepository;
import domain.view.ViewInput;
import domain.view.ViewOutput;

public class Game {
	private CardDealOut cardDealOut;
	private UserRepository userRepository;

	public void run() {
		makeUserRepository();
		cardDealOut = new CardDealOut(userRepository);
		cardDealOut.firstDealOut();
		cardDealOut.secondDealOut();
		ViewOutput.showAllResult(userRepository);
		RewardRepository rewardRepository = makeRewardeRepository();
		ViewOutput.showAllReward(rewardRepository.getRewardResult(userRepository.getUserList()));
	}
	
	public void makeUserRepository() {
		UserMaker userMaker = new UserMaker(ViewInput.getPlayerNameList());
		userRepository = new UserRepository(userMaker.makeUserList());
	}
	
	public RewardRepository makeRewardeRepository() {
		RewardCalculator rewardCalculator = new RewardCalculator(userRepository);
		
		return new RewardRepository(rewardCalculator.makeRewardList());
	}
 }