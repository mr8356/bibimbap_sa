package server;


import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	private final List<UserDto> database = new ArrayList<>();

	public void save(UserDto user) {
		database.add(user);
	}

	public List<UserDto> findAll() {
		return database;
	}

	// TODO: Singleton 패턴 적용해보기
}