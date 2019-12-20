package domain.application;

import domain.game.Game;

public class BlackJackApplication {
	
	public static void main(String[] args) {
		Game blackJack = new Game();
		
		blackJack.run();
	}

}