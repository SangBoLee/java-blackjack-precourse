package domain.user;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends User {
    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }
    
    // TODO 추가 기능 구현
    
    public Name getName() {
    	return name;
    }
    
    public String getFirstCardResult() {
    	return cards.getCardResult();
    }
    
    public BettingMoney bettingMoney() {
    	if (cards.isBlackJack()) {
    		return bettingMoney.multipleOnePointFive();
    	}
    	
    	return bettingMoney;
    }
}
