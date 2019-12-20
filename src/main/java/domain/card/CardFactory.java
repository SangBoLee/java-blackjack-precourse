package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 트럼프 카드 전체 생성을 담당하는 객체
 */
public class CardFactory {
	public static int cardSize = 52;
	private static boolean[] usedCard = new boolean[CardFactory.cardSize];
	
	private final List<Card> deck;
	
	public CardFactory() {
		deck = create();
	}
	
    public static List<Card> create() {
        List<Card> cards = new ArrayList<>();
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            createByType(cards, symbol);
        }
        return Collections.unmodifiableList(cards);
    }

    private static void createByType(List<Card> cards, Symbol symbol) {
        Type[] types = Type.values();
        for (Type type : types) {
            cards.add(new Card(symbol, type));
        }
    }
    
    public List<Card> getDeck() {
    	return deck;
    }
    
	public int cardIsUsed(int inx) throws Exception {
		if (usedCard[inx]) {
			throw new Exception();
		}
		
		usedCard[inx] = true;
		return inx;
	}
	
	public Card selectedCard() {
		return deck.get(getCardIndex());
	}
	
	public int getCardIndex() {
		try {
			return cardIsUsed(selectCard());
		} catch (Exception e) {
			return getCardIndex();
		}
	}
	
	public int selectCard() {
		return (int)(Math.random() * cardSize);
	}
}