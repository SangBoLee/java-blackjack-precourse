package domain.game;

import domain.user.PlayerNameRepository;
import domain.user.RewardRepository;
import domain.user.UserRepository;
import domain.view.ViewInput;
import domain.view.ViewOutput;

public class Game {
	private static Game blackJack = new Game();
	private CardDealOut cardDealOut;
	private PlayerNameRepository playerNameRepository;
	private UserRepository userRepository;

	public static Game getInstance() {
		return blackJack;
	}

	public void run() {
		ViewOutput viewOutput = new ViewOutput();
		
		makeUserRepository();
		cardDealOut = new CardDealOut(playerNameRepository, userRepository);
		cardDealOut.firstDealOut();
		cardDealOut.secondDealOut();
		viewOutput.showAllResult(userRepository.getUserList());
		RewardRepository rewardRepository = makeRewardeRepository();
		viewOutput.showAllReward(userRepository.getUserList(), rewardRepository.getRewardList());
	}
	
	public void makeUserRepository() {
		ViewInput viewInput = new ViewInput();
		playerNameRepository = viewInput.getPlayerNameRepository();
		UserMaker userMaker = new UserMaker(playerNameRepository.getPlayerNameList());
		userRepository = new UserRepository(userMaker.makeUserList());
	}
	
	public RewardRepository makeRewardeRepository() {
		RewardCalculator rewardCalculator = new RewardCalculator(userRepository);
		
		return new RewardRepository(rewardCalculator.makeRewardList());
	}
 }