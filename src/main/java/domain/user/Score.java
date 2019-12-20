package domain.user;

public class Score {
	private static final int SCORE_MIN = 0;
	private static final int BLACKJACK_SCORE = 21;
	private static final int TEN = 10;
	private static final int DEALER_CRITERIA = 17;
	
	public static final Score ZERO = new Score(SCORE_MIN);
	
	private final int score;
	
	public Score(int score) {
		this.score = score;
	}
	
	public Score calculate(int score) {
		return new Score(this.score + score);
	}
	
	public Score minusTen() {
		return new Score(this.score - TEN);
	}
	
	public boolean Win(Score compare) {
		return this.score > compare.toInteger();
	}
	
	public boolean isDraw(Score compare) {
		return this.score == compare.toInteger();
	}
	
	public boolean isBlackJackScore() {
		return score == BLACKJACK_SCORE;
	}
	
	public boolean isBust() {
		return score > BLACKJACK_SCORE;
	}
	
	public boolean isBelowBlackJack() {
		return score < BLACKJACK_SCORE;
	}
	
	public boolean isBelowDealerCriteria() {
		return score < DEALER_CRITERIA;
	}
	
	public int toInteger() {
		return score;
	}
}
