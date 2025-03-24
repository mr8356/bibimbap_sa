package server;

import java.util.Scanner;

public class Application {
	public static void main(String[] args) {
		// 의존성 수동 주입
		UserRepository repository = new UserRepository();
		UserService service = new UserService(repository);
		UserController controller = new UserController(service);

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n1. 사용자 등록 | 2. 사용자 전체 조회 | 0. 종료");
			String input = scanner.nextLine();

			switch (input) {
				case "1":
					System.out.print("이름 입력: ");
					String name = scanner.nextLine();
					System.out.print("이메일 입력: ");
					String email = scanner.nextLine();
					controller.createUser(name, email);
					break;
				case "2":
					controller.getAllUsers();
					break;
				case "0":
					System.out.println("종료합니다.");
					return;
				default:
					System.out.println("잘못된 입력입니다.");
			}
		}
	}
}