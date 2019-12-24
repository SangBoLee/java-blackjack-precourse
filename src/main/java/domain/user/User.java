package domain.user;

import domain.card.Card;

public abstract class User {
	protected final Cards cards = new Cards();
	
	public void addCard(Card card) {
    	cards.addCard(card);
    }
    
    public Cards toCards() {
    	return cards;
    }

	public abstract String toFirstCardResult();
	
	public abstract Name toName();
	
	public String toEachResult() {
		return toName().toString() + " 카드 : " + toCards().toCardResult();
	}
}
