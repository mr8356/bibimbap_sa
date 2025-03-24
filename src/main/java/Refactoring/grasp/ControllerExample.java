package Refactoring.grasp;

public class ControllerExample {
	public static void main(String[] args) {
		UI2 ui = new UI2();
		ui.createUser("수흠");
	}
}

class UI2 {
	public void createUser(String name) {
		// ❌ 사용자 생성 로직이 UI에 있음
		User user = new User(name);
		System.out.println("사용자 생성: " + user.name);
	}
}

class User {
	String name;

	public User(String name) {
		this.name = name;
	}
}