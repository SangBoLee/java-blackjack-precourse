package domain.user;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerNameRepository {
	private final List<Name> playerNameList;
	
	public PlayerNameRepository(List<Name> playerNameList) {
		this.playerNameList = playerNameList;
	}
	
	public List<Name> getPlayerNameList() {
		return playerNameList;
	}
	
	public String playerNames() {
		return playerNameList.stream()
							.map(Name::toString)
							.collect(Collectors.joining(", "));
	}
}
