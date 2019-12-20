package domain.view;

import java.util.Scanner;

import domain.game.PlayerNameMaker;
import domain.user.BettingMoney;
import domain.user.Name;
import domain.user.Player;
import domain.user.PlayerNameRepository;
import domain.user.PlayerNames;
import domain.user.YesOrNo;

public class ViewInput {
	private static Scanner scanner = new Scanner(System.in);
	
	public PlayerNameRepository getPlayerNameRepository() {
		try {
			System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
			PlayerNames playerNames = new PlayerNames(scanner.nextLine());
			PlayerNameMaker playerNameMaker = new PlayerNameMaker(playerNames);
			PlayerNameRepository playerNameRepository = new PlayerNameRepository(playerNameMaker.makePlayerNameList());
			return playerNameRepository;
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			return getPlayerNameRepository();
		}
	}
	
	public BettingMoney getBettingPrice(Name name) {
		try {
			System.out.println(name.toString() + "의 배팅 금액은?");
			BettingMoney bettingMoney = new BettingMoney(scanner.nextLine());
			return bettingMoney;
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			return getBettingPrice(name);
		}
	}
	
	public YesOrNo askGetCard(Player player) {
		try {
			System.out.println(player.getName() + "은(는) 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)");
			YesOrNo yesOrNo = new YesOrNo(scanner.nextLine());
			return yesOrNo;
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			return askGetCard(player);
		}
	}
}