package domain.user;

import domain.card.Card;

public abstract class User {
	protected final Cards cards = new Cards();
	
    public void addCard(Card card) {
        cards.addCard(card);
    }
    
    public Cards getCards() {
    	return cards;
    }

	public abstract String getFirstCardResult();
	
	public abstract Name getName();
}
