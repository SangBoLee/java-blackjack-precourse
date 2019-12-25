package domain.game;

import domain.user.RewardRepository;
import domain.user.UserRepository;
import domain.view.InputView;
import domain.view.OutputView;

public class Game {
    private CardDealOut cardDealOut;
    private UserRepository userRepository;

    public void run() {
        makeUserRepository();
        cardDealOut = new CardDealOut(userRepository);
        cardDealOut.firstDealOut();
        cardDealOut.secondDealOut();
        OutputView.showAllResult(userRepository);
        RewardRepository rewardRepository = makeRewardeRepository();
        OutputView.showAllReward(rewardRepository.getRewardResult(userRepository.getUserList()));
    }
	
    public void makeUserRepository() {
        UserMaker userMaker = new UserMaker(InputView.getPlayerNameList());
        userRepository = new UserRepository(userMaker.makeUserList());
    }
	
    public RewardRepository makeRewardeRepository() {
        RewardCalculator rewardCalculator = new RewardCalculator(userRepository);
		
        return new RewardRepository(rewardCalculator.makeRewardList());
    }
}