package server;

public class UserController {
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	public void createUser(String name, String email) {
		UserDto userDto = new UserDto(name, email); // → 이후 빌더 패턴으로 리팩토링
		service.saveUser(userDto);
	}

	public void getAllUsers() {
		for (UserDto user : service.getUsers()) {
			System.out.println("사용자: " + user.getName() + ", " + user.getEmail());
		}
	}
}