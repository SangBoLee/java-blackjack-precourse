package domain.game;

import domain.card.CardFactory;
import domain.user.Player;
import domain.user.PlayerNameRepository;
import domain.user.User;
import domain.user.UserRepository;
import domain.user.YesOrNo;
import domain.view.ViewInput;
import domain.view.ViewOutput;

public class CardDealOut {
	private final int firstCardCnt = 2;
	private final int playerFirstInx = 1;
	private final int dealerInx = 0;
	
	private CardFactory deck = new CardFactory();
	private UserRepository userRepository;
	private PlayerNameRepository playerNameRepository;
	
	public CardDealOut(PlayerNameRepository playerNameRepository, 
			UserRepository userRepository) {
		this.playerNameRepository = playerNameRepository;
		this.userRepository = userRepository;
	}
	
	public void firstDealOut() {
		ViewOutput viewOutput = new ViewOutput();
		giveUserCard();
		viewOutput.showFirstResult(playerNameRepository, userRepository);
	}
	
	public void giveUserCard() {
		for (User user : userRepository.getUserList()) {
			giveTwoCard(user);
		}
	}
	
	public void giveTwoCard(User user) {
		for (int i = 0; i < firstCardCnt; i++) {
			user.addCard(deck.selectedCard());
		}
	}
	
	public void secondDealOut() {
		secondPlayerDealOut();
		System.out.println();
		secondDealerDealOut();
	}
	
	public void secondPlayerDealOut() {
		for (int i = playerFirstInx; i < userRepository.getUserList().size(); i++) {
			User user = userRepository.getUserList().get(i);
			
			askOrNot(user);
		}
	}
	
	public void askOrNot(User user) {
		if (user.getCards().getScore().isBelowBlackJack()) {
			checkAnswer(user);
		}
	}
	
	public void checkAnswer(User user) {
		ViewInput viewInput = new ViewInput();
		YesOrNo yesOrNo;
		ViewOutput viewOutput = new ViewOutput();
		
		do {
			yesOrNo = viewInput.askGetCard((Player)user);
			dealOutOrNot(yesOrNo, user);
			viewOutput.showEachResult(user);
			System.out.println();
		} while (yesOrNo.isYes() && user.getCards().getScore().isBelowBlackJack());
	}
	
	public void dealOutOrNot(YesOrNo yesOrNO, User user) {
		if (yesOrNO.isYes()) {
			user.addCard(deck.selectedCard());
		}
	}
	
	public void secondDealerDealOut() {
		ViewOutput viewOutput = new ViewOutput();
		User dealer = userRepository.getUserList().get(dealerInx);
		
		while (dealer.getCards().getScore().isBelowDealerCriteria()) {
			dealer.addCard(deck.selectedCard());
			viewOutput.showDealerCheck();
		}
	}
}
