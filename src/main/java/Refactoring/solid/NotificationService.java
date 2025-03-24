package Refactoring.solid;

// DIP
//NotificationService는 문자 외에는 못 씀
//→ 나중에 카카오톡, 이메일 추가하려면 NotificationService 코드 바꿔야 함

public class NotificationService {
	private SmsSender sender = new SmsSender();

	public void notify(String message) {
		sender.send(message);
	}

	public class SmsSender {
		public void send(String msg) {
			System.out.println("문자 발송: " + msg);
		}
	}
}