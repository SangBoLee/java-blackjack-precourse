package domain.user;

public class Name {
	private final String name;
	
	public Name(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("입력된 이름이 없는 것이 있습니다.");
		}
		
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
