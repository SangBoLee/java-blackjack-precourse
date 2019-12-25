package domain.view;

import domain.user.User;
import domain.user.UserRepository;

public class OutputView {
    public static void showFirstResult(UserRepository userRepository) {
        System.out.println(userRepository.getUserNamse() + "에게 2장의 카드를 나누었습니다.");
        System.out.println(userRepository.getFirstCardResult());
    }
	
    public static void showDealerCheck() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }
	
    public static void showAllResult(UserRepository userRepository) {
        System.out.println(userRepository.getAllResult());
    }
	
    public static void showEachResult(User user) {
        System.out.println(user.toEachResult());
    }
	
    public static void showAllReward(String getAllReward) {
        System.out.println("### 최종 수익");
        System.out.println(getAllReward);
    }
}