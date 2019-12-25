package domain.user;

import java.util.List;

public class RewardRepository {
    private final List<Double> rewardList;
	
    public RewardRepository(List<Double> rewardList) {
        this.rewardList = rewardList;
    }
	
    public String getRewardResult(List<User> userList) {
        String rewardResult = "";
							
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            rewardResult += user.toName() + " : " + rewardList.get(i) + "\n";
        }
		
        return rewardResult;
    }
}
