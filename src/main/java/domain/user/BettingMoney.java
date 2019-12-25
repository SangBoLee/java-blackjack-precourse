package domain.user;

public class BettingMoney {
    private final double bettingMoney;
    private final double OnePointFive = 1.5;
	
    public BettingMoney(String bettingMoney) {
        if (bettingMoney.isEmpty()) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }
        if (bettingMoney == "0") {
            throw new IllegalArgumentException("0원을 베팅할 수 없습니다.");
        }
		
        this.bettingMoney = Integer.parseInt(bettingMoney);
    }
	
    public BettingMoney multiplyOnePointFive() {
        String money = String.valueOf(bettingMoney * OnePointFive);
        return new BettingMoney(money);
    }
	
    public double toDouble() {
        return bettingMoney;
    }
}
