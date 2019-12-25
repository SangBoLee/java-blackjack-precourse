package domain.game;

import domain.card.CardFactory;
import domain.user.Player;
import domain.user.User;
import domain.user.UserRepository;
import domain.user.YesOrNo;
import domain.view.InputView;
import domain.view.OutputView;

public class CardDealOut {
    private final int firstCardCnt = 2;
    private final int playerFirstInx = 1;
    private final int dealerInx = 0;
	
    private CardFactory deck = new CardFactory();
    private UserRepository userRepository;
	
    public CardDealOut(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
    public void firstDealOut() {
        userRepository.getUserList().stream()
                      .forEach(user -> giveTwoCard(user));
        OutputView.showFirstResult(userRepository);
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
        if (user.toCards().toScore().isBelowBlackJack()) {
            checkAnswer(user);
        }
    }
	
    public void checkAnswer(User user) {
        YesOrNo yesOrNo;
		
        do {
            yesOrNo = InputView.askGetCard((Player)user);
            dealOutOrNot(yesOrNo, user);
            OutputView.showEachResult(user);
        } while (yesOrNo.isYes() && user.toCards().toScore().isBelowBlackJack());
    }
	
    public void dealOutOrNot(YesOrNo yesOrNO, User user) {
        if (yesOrNO.isYes()) {
            user.addCard(deck.selectedCard());
        }
    }
	
    public void secondDealerDealOut() {
        User dealer = userRepository.getUserList().get(dealerInx);
		
        while (dealer.toCards().toScore().isBelowDealerCriteria()) {
            dealer.addCard(deck.selectedCard());
            OutputView.showDealerCheck();
        }
    }
}
