package domain.user;

import java.util.List;

public class RewardRepository {
	private final List<Double> rewardList;
	
	public RewardRepository(List<Double> rewardList) {
		this.rewardList = rewardList;
	}
	
	public List<Double> getRewardList() {
		return rewardList;
	}
}
