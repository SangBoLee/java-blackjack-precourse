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
    
    public Name toName() {
    	return name;
    }
    
    public String toFirstCardResult() {
    	return cards.toCardResult();
    }
    
    public BettingMoney bettingMoney() {
    	if (cards.isBlackJack()) {
    		return bettingMoney.multiplyOnePointFive();
    	}
    	
    	return bettingMoney;
    }
}
