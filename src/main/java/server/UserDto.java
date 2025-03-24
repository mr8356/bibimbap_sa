package server;

public class UserDto {
	private final String name;
	private final String email;

	public UserDto(String name, String email) {
		this.name = name;
		this.email = email;
	}

	// TODO: Builder 패턴으로 리팩토링 해보기

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}