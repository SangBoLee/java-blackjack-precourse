package domain.game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.user.Name;
import domain.user.PlayerNames;

public class PlayerNameMaker {
    PlayerNames playerNames;
	
    public PlayerNameMaker(PlayerNames playerNames) {
        this.playerNames = playerNames;
    }
	
    public List<Name> makePlayerNameList() {
        List<Name> playerNameList;
        String[] playerNameArray = playerNames.toString().split(",", -1);
		
        playerNameList = ArrayToList(playerNameArray);
		
        isException(playerNameList);
        System.out.println();
		
        return playerNameList;
    }
	
    public void isException(List<Name> playerNameList) {
        if (!areAllDifferent(playerNameList)) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
            }
    }
	
    public List<Name> ArrayToList(String[] playerNameArray) {
        return Arrays.stream(playerNameArray)
                     .map(String::trim)
                     .map(name -> new Name(name))
                     .collect(Collectors.toList());
    }
	
    public boolean areAllDifferent(List<Name> playerNameList) {
        int firstSize = playerNameList.size();
        List<String> distinctName = playerNameList.stream()
                                                  .map(Name::toString)
                                                  .distinct()
                                                  .collect(Collectors.toList());
        return firstSize == distinctName.size();
    }
}
