package domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class Cards {
	private final static int BLACKJACK_CARDCNT = 2;
	private final static int FIRSTCARD = 1;
	
	private final List<Card> cards = new ArrayList<Card>();
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public String toFirstCard() {
		return cards.get(FIRSTCARD).toString();
	}
	
	public String toCardResult() {
		return cards.stream()
				.map(Card::toString)
				.collect(Collectors.joining(", "));
	}
	
	public Score toScore() {
		return calculateRealScore(calculateRawScore());
	}
	
	private Score calculateRawScore() {
		Score score = Score.ZERO;
		
		for (Card card : cards) {
			score = score.calculate(card.toSymbol().toScore());
		}
		
		return score;
	}
	
	private Score calculateRealScore(Score score) {
		if (score.isBust() && hasAce()) {
			return score.minusTen();
		}
		return score;
	}
	
	private boolean hasAce() {
		return cards.stream()
					.filter(card -> card.toSymbol().isAce())
					.findFirst()
					.isPresent();
	}
	
	public boolean isBlackJack() {
		return (cards.size() == BLACKJACK_CARDCNT && toScore().isBlackJackScore());
	}
}
