package domain.user;

public class YesOrNo {
    private final int charLength = 1;
    private final char agree = 'y';
    private final char disagree = 'n';
    private final char value;
	
    public YesOrNo(String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("입력된 값이 없습니다.");
        }
        if (value.length() > charLength) {
            throw new IllegalArgumentException("입력 문자가 두 자 이상이 될 수 없습니다.");
        }
		
        this.value = Character.toLowerCase(value.charAt(0));
        if (this.value != agree && this.value != disagree) {
            throw new IllegalArgumentException("입력 문자는 y(Y), n(N)만 가능합니다.");
        }
    }

    public boolean isYes() {
        return value == agree;
    }
}
