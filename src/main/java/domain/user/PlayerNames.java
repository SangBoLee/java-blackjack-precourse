package domain.user;

public class PlayerNames {
    private final String playerNames;
	
    public PlayerNames(String playerNames) {
        if (playerNames.trim().isEmpty()) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }
        if (playerNames.trim().isEmpty()) {
            throw new IllegalArgumentException("문자만 입력해야합니다.");
        }
		
        this.playerNames = playerNames;
    }
	
    public String toString() {
        return playerNames;
    }
}
