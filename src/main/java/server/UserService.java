package server;

import java.util.List;

public class UserService {
	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public void saveUser(UserDto user) {
		repository.save(user);
	}

	public List<UserDto> getUsers() {
		return repository.findAll();
	}
}