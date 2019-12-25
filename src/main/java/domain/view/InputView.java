package domain.view;

import java.util.List;
import java.util.Scanner;

import domain.game.PlayerNameMaker;
import domain.user.BettingMoney;
import domain.user.Name;
import domain.user.Player;
import domain.user.PlayerNames;
import domain.user.YesOrNo;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
	
    public static List<Name> getPlayerNameList() {
        try {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            PlayerNames playerNames = new PlayerNames(scanner.nextLine());
            PlayerNameMaker playerNameMaker = new PlayerNameMaker(playerNames);
            return playerNameMaker.makePlayerNameList();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return getPlayerNameList();
        }
    }
	
    public static BettingMoney getBettingPrice(Name name) {
        try {
            System.out.println(name.toString() + "의 배팅 금액은?");
            BettingMoney bettingMoney = new BettingMoney(scanner.nextLine());
            return bettingMoney;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return getBettingPrice(name);
        }
    }
	
    public static YesOrNo askGetCard(Player player) {
        try {
            System.out.println(player.toName() + "은(는) 한장의 카드를 더 받겠습니까?(예는 y,아니오는 n)");
            YesOrNo yesOrNo = new YesOrNo(scanner.nextLine());
            return yesOrNo;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return askGetCard(player);
        }
    }
}